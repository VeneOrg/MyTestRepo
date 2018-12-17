package com.example.datasource_model;

//In spring, Repository means data_source

import com.example.domain_model.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.domain_model.Identifiable;
import org.thymeleaf.util.StringUtils;

public abstract class InMemoryRepository<T extends Car >{  //Generics are used here where T type not known(Read wildcards? t k e v in generics-wiki)
    @Autowired
    private IdGenerator idGenerator;

    private List<T> elements = Collections.synchronizedList(new ArrayList<>());

    public T create(T element) {
        elements.add(element);
        element.setId(idGenerator.getNextId());
        return element;
    }

    public boolean delete(Long id) {
        return elements.removeIf(element -> element.getId().equals(id));
    }

    public List<T> findAll() {
            return elements;
    }

    public List<T> findAllByQuery(String brand) {
        //return elements;
        //return elements.stream().filter(r -> r.getBrand().equals(brand));
        return elements.stream().filter(r -> r.getBrand().equals(brand)).collect(Collectors.toList());
    }

    public Optional<T> findById(Long id) {
        return elements.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
    //Optional - A container object which may or may not contain a non-null value. If a value is present,
    // isPresent() will return true and get() will return the value.
//Additional methods that depend on the presence or absence of a contained value are provided, such as orElse()
// (return a default value if value not present) and ifPresent() (execute a block of code if the value is present).

    public int getCount() {
        return elements.size();
    }

    public void clear() {
        elements.clear();
    }

    public boolean update(Long id, T updated) {

        if (updated == null) {
            return false;
        }
        else {
            Optional<T> element = findById(id);
            element.ifPresent(original -> updateIfExists(original, updated));
            return element.isPresent();
        }
    }

    protected abstract void updateIfExists(T original, T desired);
}
