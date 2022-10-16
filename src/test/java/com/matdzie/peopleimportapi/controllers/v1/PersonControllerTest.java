package com.matdzie.peopleimportapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
import com.matdzie.peopleimportapi.api.v1.model.PersonListDto;
import com.matdzie.peopleimportapi.controllers.ControllerAdviceExceptionHandler;
import com.matdzie.peopleimportapi.exceptions.PersonNotFoundException;
import com.matdzie.peopleimportapi.services.PersonService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PersonControllerTest {

    @InjectMocks
    PersonController sut;

    @Mock
    PersonService personService;

    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(sut)
                .setControllerAdvice(new ControllerAdviceExceptionHandler())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void findByIdOk() throws Exception {
        Long id = 523L;
        var personDto = new PersonDto("X", 1, 999999);
        var personDtoJson = objectMapper.writeValueAsString(personDto);

        when(personService.findById(id)).thenReturn(personDto);

        mockMvc.perform(get(PersonController.BASE_URI + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(personDtoJson));

        verify(personService, times(1)).findById(id);
    }

    @Test
    void findByIdPersonNotFound() throws Exception {
        Long id = 3L;

        when(personService.findById(id)).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get(PersonController.BASE_URI + "/" + id))
                .andExpect(status().isNotFound());

        verify(personService, times(1)).findById(id);
    }

    @Test
    void findByNameOk() throws Exception {
        String name = "o";
        var personListDto = new PersonListDto(Arrays.asList(
                new PersonDto("Ro3", 10, 10),
                new PersonDto("nOn", 211, 120)));
        var personListDtoJson = objectMapper.writeValueAsString(personListDto);

        when(personService.findByName(name)).thenReturn(personListDto);

        var uri = UriComponentsBuilder.fromUriString(PersonController.BASE_URI).queryParam("name", name).build();
        mockMvc.perform(get(uri.toString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(personListDtoJson));

        verify(personService, times(1)).findByName(name);
    }

    @Test
    void findByNamePersonNotFound() throws Exception {
        String name = "Not existing name";

        when(personService.findByName(name)).thenThrow(PersonNotFoundException.class);

        var uri = UriComponentsBuilder.fromUriString(PersonController.BASE_URI).queryParam("name", name).build();
        mockMvc.perform(get(uri.toString()))
                .andExpect(status().isNotFound());

        verify(personService, times(1)).findByName(name);
    }

    @Test
    void importByIdOk() throws Exception {
        Long id = 23L;

        mockMvc.perform(get(PersonController.BASE_URI + "/import/" + id))
                .andExpect(status().isOk());

        verify(personService, times(1)).importById(id);
    }

    @Test
    void importByIdConstraintViolationException() throws Exception {
        Long id = 84L;

        doThrow(ConstraintViolationException.class).when(personService).importById(id);
        mockMvc.perform(get(PersonController.BASE_URI + "/import/" + id))
                .andExpect(status().isConflict());

        verify(personService, times(1)).importById(id);
    }
}