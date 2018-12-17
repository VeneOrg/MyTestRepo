package com.example.PresentationResource;

import com.example.domain_model.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/*
We inherit from the ResourceSupport class provided by the Spring HATEOAS packages,
which allows us to attach links to our resource
Final is used because we wish to restrict the values of the fields in the resource from changing
after they have been set, ensuring that they reflect the values of the Car class for which it is acting as a resource.
 */
public class CarResource extends ResourceSupport {
    private final Long id;
    private final String brand;
    private final Long model_number;
    private final String car_type;
    private final String car_details;

    public CarResource(Car car){
        id = car.getId();
        brand = car.getBrand();
        model_number = car.getModel_number();
        car_type = car.getCar_type();
        car_details = car.getCar_details();
    }

    /*
    We cannot use the getId()method as our getter for our ID since the ResourceSupport class has a default getId()
    method that returns a link. Therefore, we use the getResourceId() method as our getter for our id field; thus,
    we have to annotate our getResourceId() method since, by default, our resource would serialize the ID field to
    resourceId due to the name of the getter method.
    To force this property to be serialized to id, we use the @JsonProperty("id") annotation.
     */
    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getCar_details() {
        return car_details;
    }

    public String getCar_type() {
        return car_type;
    }

    public Long getModel_number() {
        return model_number;
    }

    public String getBrand() {
        return brand;
    }
}
