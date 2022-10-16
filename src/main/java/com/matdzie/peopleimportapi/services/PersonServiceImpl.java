package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.mapper.PersonMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;
import com.matdzie.peopleimportapi.exceptions.PersonNotFoundException;
import com.matdzie.peopleimportapi.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonServiceImpl implements PersonService {

    private final String importApiUrl;
    private final RestTemplate restTemplate;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    public PersonServiceImpl(@Value("${person.import.api.url}") String importApiUrl, RestTemplate restTemplate, PersonMapper personMapper, PersonRepository personRepository) {
        this.importApiUrl = importApiUrl;
        this.restTemplate = restTemplate;
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        var person = personMapper.personDtoToPerson(personDto);
        return personMapper.personToPersonDto(personRepository.save(person));
    }

    @Override
    public PersonDto findById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::personToPersonDto)
                .orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public PersonListDto findByName(String name) {
        var persons = personRepository.findByNameContainsIgnoreCase(name);
        if (persons.isEmpty()) throw new PersonNotFoundException();
        return new PersonListDto(personMapper.personsToPersonDtos(persons));
    }

    @Override
    public void importById(Long id) {
        var personDto = restTemplate.getForObject(importApiUrl + id, PersonDto.class);
        personRepository.save(personMapper.personDtoToPerson(personDto));
    }
}
