package com.example.PresentationResource;

import com.example.domain_model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class CarResourceAssembler extends ResourceAssembler<Car, CarResource> {
    @Autowired
    protected EntityLinks entityLinks;
    //Ussing entityLinks we can create a link to our own resource by specifying (using the linkToSingleResource method) that we wish to create
    // a link to an Order , which uses the Spring HATEOAS Identifiable interface to obtain the ID of the object.
    // We then reuse this link to create three separate links: (1) a self link, (2) an update link, and (3) a delete link.

    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";

    @Override
    public CarResource toResource(Car car) {

        CarResource resource = new CarResource(car);

        final Link selfLink = entityLinks.linkToSingleResource(car);

        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE_REL));
        resource.add(selfLink.withRel(DELETE_REL));

        return resource;
    }
}
