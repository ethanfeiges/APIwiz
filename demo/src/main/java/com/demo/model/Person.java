package com.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.UUID;
/* Database model for a person
Person has properties for a name and id
 Arguments in constructor:
    id: Unique person identifier
    name: person's name
 */

public class Person {
    private final UUID id;
    private final String name;

    // @JsonProperties are specified to allow client to submit a JSON object body
    // Client can create a Person object through the HTTP body using the Person models
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}
