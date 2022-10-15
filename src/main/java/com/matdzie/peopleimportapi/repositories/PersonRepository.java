package com.matdzie.peopleimportapi.repositories;

import com.matdzie.peopleimportapi.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByNameContainsIgnoreCase(String partialName);
}
