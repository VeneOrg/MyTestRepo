package com.example.Controller;

import com.example.PresentationResource.CarResource;
import com.example.PresentationResource.CarResourceAssembler;
import com.example.datasource_model.CarRepository;
import com.example.domain_model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/*
ResponseEntity represents the whole HTTP response: status code, headers, and body. Because of it, we can use it to fully configure the HTTP response.

If we want to use it, we have to return it from the endpoint; Spring takes care of the rest.

ResponseEntity is a generic type. As a result, we can use any type as the response body
 */

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Car.class)
@RequestMapping(value = "/car", produces = "application/json")
public class CarController {
    @Autowired
    private CarRepository repository;

    @Autowired
    private CarResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<CarResource>> findAllCars(
            @RequestParam(value = "brand", required = false) String brand
    ) {
        List<Car> cars;
        if(brand == null || brand.isEmpty()) {
             cars = repository.findAll();
        }
        else {
            cars = repository.findAllByQuery(brand);
        }
        return new ResponseEntity<>(assembler.toResourceCollection(cars), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<CarResource> createCar(@RequestBody Car car) {
        Car createdCar = repository.create(car);
        return new ResponseEntity<>(assembler.toResource(createdCar), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CarResource> findCarById(@PathVariable Long id) {
        Optional<Car> car = repository.findById(id);

        if (car.isPresent()) {
            return new ResponseEntity<>(assembler.toResource(car.get()), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        boolean wasDeleted = repository.delete(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<CarResource> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        boolean wasUpdated = repository.update(id, updatedCar);

        if (wasUpdated) {
            return findCarById(id);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
