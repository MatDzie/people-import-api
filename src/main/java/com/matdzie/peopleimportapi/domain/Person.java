package com.matdzie.peopleimportapi.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "height", "mass"})
})
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false, unique = true)
    private Long id;

    @NotEmpty
    @NotBlank
    private String name;

    @NotEmpty
    @NotBlank
    private String height;

    @NotEmpty
    @NotBlank
    private String mass;

    public Person() {
    }

    public Person(Long id, String name, String height, String mass) {
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
}