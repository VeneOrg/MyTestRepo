package com.example.domain_model;

//Domain model will give the definition of the car

/*
We need a unique ID for each car registered, so we extend the Identifiable interface provided by the Spring HATEOAS framework.
This interface provides a getId() method and extending this interface allows us to
use our domain class within the Spring HATEOAS framework, which will come in handy
when we implement the presentation layer.
 */

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long>{
    void setId(Long id);
}
