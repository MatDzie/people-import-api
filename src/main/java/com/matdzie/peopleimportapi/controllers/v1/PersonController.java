package com.matdzie.peopleimportapi.controllers.v1;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;
import com.matdzie.peopleimportapi.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(PersonController.BASE_URI)
public class PersonController {

    public static final String BASE_URI = "/api/v1/persons";
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDto save(@RequestBody @Valid PersonDto personDto) {
        return personService.save(personDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public PersonListDto findByName(@RequestParam String name) {
        return personService.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/import/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void importById(@PathVariable Long id) {
        personService.importById(id);
    }
}