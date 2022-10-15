package com.matdzie.peopleimportapi.api.v1.mapper;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);

    List<PersonDto> personsToPersonDtos(List<Person> persons);
}