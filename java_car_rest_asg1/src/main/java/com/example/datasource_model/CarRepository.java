package com.example.datasource_model;

import com.example.domain_model.Car;
import org.springframework.stereotype.Repository;

//@Repository annotation is the same as the@Component annotation
// (allows our class to be injected into other classes by the Spring DI framework)
// but also includes additional database-related capabilities.
@Repository
public class CarRepository extends InMemoryRepository<Car>{
    protected void updateIfExists(Car original, Car updated) {
        original.setCar_details(updated.getCar_details());
        original.setBrand(updated.getBrand());
        original.setModel_number(updated.getModel_number());
    }
}
