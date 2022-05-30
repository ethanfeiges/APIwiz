package com.demo.dao;
// Interface where the operations allowed are defined
// Defined for actual contract for anyone that wishes to implement interface
import com.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


// Accesses the data (DAO means Data Access Object)
// Can be injected
public interface PersonDao {
    // Insert a person object into the database
    int insertPerson(UUID id, Person person);

    // If client does not specify an ID when calling insertPerson
    // Returns the object
    default int insertPerson(Person person){
        // Randomly generates ID
        UUID id = UUID.randomUUID();
        // returns result of implemented method with generated ID
        return insertPerson(id, person);
    }

    //Methods toe implemented
    List<Person> selectAllPeople();

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);

    Optional<Person> selectPersonById(UUID id);
}
