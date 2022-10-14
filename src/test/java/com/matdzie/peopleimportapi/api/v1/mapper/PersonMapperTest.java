package com.matdzie.peopleimportapi.api.v1.mapper;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonMapperTest {

    PersonMapper sut;

    @BeforeEach
    void setUp() {
        sut = new PersonMapperImpl();
    }

    @Test
    void personToPersonDto() {
        var person = new Person(5L, "Joe", 200, 100);

        var personDto = sut.personToPersonDto(person);

        assertEquals(person.getId(), personDto.getId());
        assertEquals(person.getName(), personDto.getName());
        assertEquals(person.getHeight(), personDto.getHeight());
        assertEquals(person.getMass(), personDto.getMass());
    }

    @Test
    void personDtoToPerson() {
        var personDto = new PersonDto(2L, "", 0, 0);

        var person = sut.personDtoToPerson(personDto);

        assertEquals(personDto.getId(), person.getId());
        assertEquals(personDto.getName(), person.getName());
        assertEquals(personDto.getHeight(), person.getHeight());
        assertEquals(personDto.getMass(), person.getMass());
    }
}
