package com.matdzie.peopleimportapi.repositories;

import com.matdzie.peopleimportapi.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
