package com.matdzie.peopleimportapi.controllers.v1;

import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;
import com.matdzie.peopleimportapi.services.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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
    @ApiOperation(value = "Adds new person",
            notes = "Duplicates are not allowed")
    public PersonDto save(@RequestBody @Valid PersonDto personDto) {
        return personService.save(personDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Updates person with given id",
            notes = "Duplicates are not allowed")
    public PersonDto update(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.update(id, personDto);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find persons by name",
            notes = "Search policy: contains, ignore case")
    public PersonListDto findByName(@RequestParam String name) {
        return personService.findByName(name);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find person by id")
    public PersonDto findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/import/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(code = 409, message = "Already exists")
    @ApiOperation(value = "Import person by id from swapi.dev",
            notes = "Returns imported person\nDuplicates are not allowed\nOriginal id of imported person is not preserved")
    public PersonDto importById(@PathVariable Long id) {
        return personService.importById(id);
    }
}