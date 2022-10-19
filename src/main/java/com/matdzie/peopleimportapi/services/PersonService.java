package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;

public interface PersonService {

    PersonDto save(PersonDto personDto);

    PersonDto update(Long id, PersonDto personDto);

    PersonDto findById(Long id);

    PersonListDto findByName(String name);

    PersonDto importById(Long id);
}
