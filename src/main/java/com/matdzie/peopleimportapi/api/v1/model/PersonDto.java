package com.matdzie.peopleimportapi.api.v1.model;

public class PersonDto {

    private Long id;
    private String name;
    private Integer height;
    private Integer mass;

    public PersonDto(Long id, String name, Integer height, Integer mass) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }
}
