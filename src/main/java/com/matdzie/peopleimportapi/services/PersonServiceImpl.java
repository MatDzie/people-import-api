package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.mapper.PersonMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
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
    public void importById(Long id) {
        var personDto = restTemplate.getForObject(importApiUrl + id, PersonDto.class);
        personRepository.save(personMapper.personDtoToPerson(personDto));
    }
}
