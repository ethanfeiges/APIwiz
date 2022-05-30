package com.demo.api;

import com.demo.model.Person;
import com.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// APIs are named as "controllers" in Java Spring Boot
// Specifies path for API endpoint
@RequestMapping("api/v1/person")


// @RestController allows us to use this class as a RESTful API
@RestController
// Accesses the personService class
public class PersonController {
    // Allows us to access methods within the PersonService "service"
    private final PersonService personService;

    // Injecting service into constructor
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    // Specifies that addPerson method should be used when a POST request is made
    @PostMapping
    // @RequestBody specifies that parameter is passed in with a Client POST request
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    // Specifies that getAllPeople should be used when a GET request is made
    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }


    // Method is called when client sends GET request with id in path
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        // Returns either the person with the ID or null if no person has the passed in ID
        return personService.getPersonById(id).orElse(null);
    }
    // Method is called when client sends DELETE request with id in path
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }
    // Method is called when client sends PUT request with person in body and id in path
    // Updates person with given id with Person object passed in through the HTTP request body
    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
