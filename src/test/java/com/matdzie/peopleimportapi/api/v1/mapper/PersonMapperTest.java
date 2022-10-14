package com.matdzie.peopleimportapi.api.v1.mapper;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonMapperImpl.class})
class PersonMapperTest {

    @Autowired
    PersonMapper personMapper;

    @Test
    void personToPersonDto() {
        var person = new Person(5L, "Joe", 200, 100);

        var personDto = personMapper.personToPersonDto(person);

        assertEquals(person.getId(), personDto.getId());
        assertEquals(person.getName(), personDto.getName());
        assertEquals(person.getHeight(), personDto.getHeight());
        assertEquals(person.getMass(), personDto.getMass());
    }

    @Test
    void personDtoToPerson() {
        var personDto = new PersonDto(2L, "", 0, 0);

        var person = personMapper.personDtoToPerson(personDto);

        assertEquals(personDto.getId(), person.getId());
        assertEquals(personDto.getName(), person.getName());
        assertEquals(personDto.getHeight(), person.getHeight());
        assertEquals(personDto.getMass(), person.getMass());
    }
}
