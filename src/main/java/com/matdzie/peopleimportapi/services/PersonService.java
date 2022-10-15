package com.matdzie.peopleimportapi.services;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;

public interface PersonService {

    PersonDto findById(Long id);

    PersonListDto findByName(String name);

    void importById(Long id);
}
