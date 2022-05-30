package com.demo.dao;
import com.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.List;
/*Class serves as a repository, so it needs to be instantiated
Makes it possible to inject it in other classes
*/

// Specifies unique implementation of personDao
@Repository("fakeDao")
// Implements the DAO interface to access and manipulate data
public class FakePersonDataAccessService implements PersonDao{
    // Database of people is stored in an ArrayList
    public static List<Person> DB = new ArrayList<>();
    @Override
    // Adds new Person object to DB with custom ID
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        // Insertion will always work
        return 1;
    }

    @Override
    // Adds new Person object to DB with generated ID
    public int insertPerson(Person person) {
        // calls interface method that generates ID
        return PersonDao.super.insertPerson(person);
    }

    @Override
    // Returns all People in DB
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    // Deletes a person in the DB with given id
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        // Remove person from DB
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override

    /*  Updates a Person in the DB with a given ID

        Parameters:
        id: Specific to each person and passed as a path variable by client
        update: Person object passed in as part of the request body (no ID specified)
     */
    public int updatePersonById(UUID id, Person update) {
        // select and map person "p" if present
        return selectPersonById(id).map(p->{
            // Find index of found person
            int indexOfPersonToUpdate = DB.indexOf(p);
            // if a person is found
            if(indexOfPersonToUpdate >= 0){
                // Create a new Person object to be inserted into DB WITH id
                DB.set(indexOfPersonToUpdate, new Person(id, update.getName()));
                return 1;
            }
            else{
                return 0;
            }
        }).orElse(0);
    }

    @Override
    // Finds the first person object in the DB that possesses the specified id
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

}
