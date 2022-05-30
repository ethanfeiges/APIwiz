package com.demo.service;

import com.demo.dao.PersonDao;
import com.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Makes it clear that this class acts as a service
@Service
// Service calls the PersonDAO methods
public class PersonService {
    private final PersonDao personDao;
    // Injecting interface into the actual constructor
    @Autowired
    // @Qualifier specifies which implementation we are injecting
    // (Using the implementation from FakePersonDataAccessService)
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        // returns the reference
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }


    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }

}
