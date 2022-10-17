package com.matdzie.peopleimportapi.api.v1.mapper;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonMapperTest {

    PersonMapper sut;

    @BeforeEach
    void setUp() {
        sut = new PersonMapperImpl();
    }

    @Test
    void personToPersonDto() {
        var person = new Person(5L, "Joe", "200", "100");

        var personDto = sut.personToPersonDto(person);

        assertEquals(person.getName(), personDto.getName());
        assertEquals(person.getHeight(), personDto.getHeight());
        assertEquals(person.getMass(), personDto.getMass());
    }

    @Test
    void personDtoToPerson() {
        var personDto = new PersonDto("z", "2", "3");

        var person = sut.personDtoToPerson(personDto);

        assertEquals(personDto.getName(), person.getName());
        assertEquals(personDto.getHeight(), person.getHeight());
        assertEquals(personDto.getMass(), person.getMass());
    }

    @Test
    void personsToPersonDtos() {
        var persons = Arrays.asList(
                new Person(13L, "Foo", "35", "140"),
                new Person(1313L, "Bar", "35324545", "1"),
                new Person(2L, "Baz", "425", "4223232"));

        var personDtos = sut.personsToPersonDtos(persons);

        for (int i = 0; i < persons.size(); ++i) {
            assertEquals(persons.get(i).getName(), personDtos.get(i).getName());
            assertEquals(persons.get(i).getHeight(), personDtos.get(i).getHeight());
            assertEquals(persons.get(i).getMass(), personDtos.get(i).getMass());
        }
    }
}
