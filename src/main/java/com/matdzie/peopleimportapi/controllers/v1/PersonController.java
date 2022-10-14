package com.matdzie.peopleimportapi.controllers.v1;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PersonController.BASE_URL)
public class PersonController {

    public static final String BASE_URL = "/api/v1/persons";

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getById(@PathVariable Long id) {
        return personService.getById(id);
    }

    @GetMapping("/import/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void importById(@PathVariable Long id) {
        personService.importById(id);
    }
}