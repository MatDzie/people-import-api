package com.matdzie.peopleimportapi.api.v1.model;

import com.matdzie.peopleimportapi.validation.PersonHeightConstrain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class PersonDto {

    @NotEmpty
    @NotBlank
    private String name;

    @PersonHeightConstrain
    private String height;

    @NotEmpty
    @NotBlank
    private String mass;

    public PersonDto() {
    }

    public PersonDto(String name, String height, String mass) {
        this.name = name;
        this.height = height;
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return name.equals(personDto.name) && height.equals(personDto.height) && mass.equals(personDto.mass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, mass);
    }
}
