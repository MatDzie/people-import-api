package com.matdzie.peopleimportapi.api.v1.model;

import com.matdzie.peopleimportapi.validation.PersonHeightConstrain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PersonDto {

    @NotEmpty
    @NotBlank
    private String name;

    @PersonHeightConstrain
    private Integer height;

    @Min(1)
    @NotNull
    private Integer mass;

    public PersonDto() {
    }

    public PersonDto(String name, Integer height, Integer mass) {
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

    @Override
    public int hashCode() {
        return Objects.hash(name, height, mass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return name.equals(personDto.name) && height.equals(personDto.height) && mass.equals(personDto.mass);
    }
}
