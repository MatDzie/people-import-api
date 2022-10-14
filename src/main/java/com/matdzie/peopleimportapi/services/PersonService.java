package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;

public interface PersonService {

    PersonDto getById(Long id);

    void importById(Long id);
}
