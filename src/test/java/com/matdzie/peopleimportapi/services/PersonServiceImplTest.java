package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.mapper.PersonMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import com.matdzie.peopleimportapi.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    PersonServiceImpl sut;

    String importApiUrl = "https://fake/api/url/";
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
    void importById() {
        Long id = 2L;
        String name = "Luke";
        Integer height = 120;
        Integer mass = 50;
        var personDto = new PersonDto(id, name, height, mass);
        var person = new Person(id, name, height, mass);

        when(restTemplate.getForObject(anyString(), eq(PersonDto.class))).thenReturn(personDto);
        when(personMapper.personDtoToPerson(personDto)).thenReturn(person);
        sut.importById(id);

        verify(restTemplate, times(1)).getForObject(importApiUrl + id, PersonDto.class);
        verify(personMapper, times(1)).personDtoToPerson(personDto);
        verify(personRepository, times(1)).save(person);
    }
}