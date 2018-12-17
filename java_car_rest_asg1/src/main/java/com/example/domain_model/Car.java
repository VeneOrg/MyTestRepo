package com.example.domain_model;

public class Car implements Identifiable{
    private Long id;
    private String brand;
    private Long model_number;
    private String car_type;
    private String car_details;

    // We have used Plain Old Java Objects (POJOs) that are independent of the
    // rest of the intricacies of our web service. No complicated business logic present.

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {return id;}

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel_number(Long model_number) {this.model_number = model_number;}

    public Long getModel_number() {
        return model_number;
    }

    public void setCar_type(String car_type) {this.car_type = car_type; }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_details(String car_details) {this.car_details = car_details;}

    public String getCar_details() {
        return car_details;
    }
}
