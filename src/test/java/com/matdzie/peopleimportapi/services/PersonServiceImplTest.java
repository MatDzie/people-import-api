package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.mapper.PersonMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import com.matdzie.peopleimportapi.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    final static String importApiUrl = "https://fake/api/url/";
    PersonService sut;
    @Mock
    RestTemplate restTemplate;
    @Mock
    PersonMapper personMapper;
    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new PersonServiceImpl(importApiUrl, restTemplate, personMapper, personRepository);
    }

    @Test
    void saveOk() {
        Long id = 3123L;
        String name = "X";
        Integer height = 1;
        Integer mass = 20;
        var person = new Person(id, name, height, mass);
        var personDto = new PersonDto(name, height, mass);

        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.personToPersonDto(person)).thenReturn(personDto);
        when(personMapper.personDtoToPerson(personDto)).thenReturn(person);

        var result = sut.save(personDto);

        assertEquals(personDto, result);
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).personToPersonDto(person);
        verify(personMapper, times(1)).personDtoToPerson(personDto);
    }

    @Test
    void findByIdOk() {
        Long id = 9999L;
        String name = "Vader";
        Integer height = 300;
        Integer mass = 200;
        var person = new Person(id, name, height, mass);
        var personDto = new PersonDto(name, height, mass);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));
        when(personMapper.personToPersonDto(person)).thenReturn(personDto);

        var result = sut.findById(id);

        assertEquals(personDto, result);
        verify(personRepository, times(1)).findById(id);
        verify(personMapper, times(1)).personToPersonDto(person);
    }

    @Test
    void findByIdPersonNotFound() {
        Long id = 4L;

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        var thrown = assertThrows(ResponseStatusException.class, () -> sut.findById(id));

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        verify(personRepository, times(1)).findById(id);
    }

    @Test
    void findByNameOk() {
        String name = "a";
        var persons = Arrays.asList(
                new Person(3L, "Anna", 23, 32),
                new Person(4L, "Franek Kimono", 2, 4343));

        var personDtos = Arrays.asList(
                new PersonDto("Anna", 23, 32),
                new PersonDto("Franek Kimono", 2, 4343));

        when(personRepository.findByNameContainsIgnoreCase(name)).thenReturn(persons);
        when(personMapper.personsToPersonDtos(persons)).thenReturn(personDtos);
        var results = sut.findByName(name).getResults();

        assertEquals(personDtos, results);
        verify(personRepository, times(1)).findByNameContainsIgnoreCase(name);
    }

    @Test
    void findByNamePersonNotFound() {
        String name = "Not existing name";
        List<Person> emptyPersonsList = new ArrayList<>();

        when(personRepository.findByNameContainsIgnoreCase(name)).thenReturn(emptyPersonsList);

        var thrown = assertThrows(ResponseStatusException.class, () -> sut.findByName(name));

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatus());
        verify(personRepository, times(1)).findByNameContainsIgnoreCase(name);
    }

    @Test
    void importById() {
        Long id = 2L;
        String name = "Luke";
        Integer height = 120;
        Integer mass = 50;
        var personDto = new PersonDto(name, height, mass);
        var person = new Person(id, name, height, mass);

        when(restTemplate.getForObject(anyString(), eq(PersonDto.class))).thenReturn(personDto);
        when(personMapper.personDtoToPerson(personDto)).thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);
        when(personMapper.personToPersonDto(person)).thenReturn(personDto);

        var savedPersonDto = sut.importById(id);

        assertEquals(personDto, savedPersonDto);
        verify(restTemplate, times(1)).getForObject(importApiUrl + id, PersonDto.class);
        verify(personMapper, times(1)).personDtoToPerson(personDto);
        verify(personRepository, times(1)).save(person);
        verify(personMapper, times(1)).personToPersonDto(person);
    }
}