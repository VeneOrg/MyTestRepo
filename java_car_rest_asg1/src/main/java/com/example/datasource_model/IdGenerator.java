package com.example.datasource_model;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;
/*
This annotation allows our Spring boot application to recognize (through component scanning) our generator class
 as an injectable component. This will allow us to use the@Autowired annotation in other classes and have our component
 injected without having to create the generator using the new operator.
*/
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IdGenerator {
    private AtomicLong nextId = new AtomicLong(1);
    // Atomic helps in avoiding race condition, where two identical IDs may be produced for concurrent requests.

    public long getNextId() {
        return nextId.getAndIncrement();
    }
}


/*
@scope is used which ensures that a new object is created each time is it autowired.
If we inject an ID generator into two data sources and don't use PROTOTYPE it would result in inconsistent ID generation.
Using PROTOTYPE the IDs can be differentiated based on the type of the object, i.e. "this is order 1" and "this is user 1")
 */