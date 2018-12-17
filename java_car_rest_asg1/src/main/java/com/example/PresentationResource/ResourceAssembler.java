package com.example.PresentationResource;

import java.util.Collection;
import java.util.stream.Collectors;

//This assembler will create a CarResource from an Car domain object.

public abstract class ResourceAssembler<DomainType, ResourceType> {
    public abstract ResourceType toResource(DomainType domainObject);
    //consumes a single Car object and produces a CarResource object

    public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects) {
        return domainObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
    }
    //consumes a collection of Car objects and produces a collection of CarResource objects.
    //In this method(toResourceCollection) we have mapped the consumed list of Order objects to OrderResource objects by calling the toResource
    // method on each of the Order objects in the consumed list.
}
