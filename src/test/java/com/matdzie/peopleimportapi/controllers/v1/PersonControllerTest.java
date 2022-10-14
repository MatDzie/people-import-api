package com.matdzie.peopleimportapi.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matdzie.peopleimportapi.api.v1.model.PersonDto;
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
    void getByIdOk() throws Exception {
        Long id = 523L;
        var personDto = new PersonDto(id, "X", 1, 999999);
        var personDtoJson = objectMapper.writeValueAsString(personDto);

        when(personService.getById(id)).thenReturn(personDto);

        mockMvc.perform(get(PersonController.BASE_URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(personDtoJson));

        verify(personService, times(1)).getById(id);
    }

    @Test
    void getByIdPersonNotFound() throws Exception {
        Long id = 3L;

        when(personService.getById(id)).thenThrow(PersonNotFoundException.class);

        mockMvc.perform(get(PersonController.BASE_URL + "/" + id))
                .andExpect(status().isNotFound());

        verify(personService, times(1)).getById(id);
    }

    @Test
    void importByIdOk() throws Exception {
        Long id = 23L;

        mockMvc.perform(get(PersonController.BASE_URL + "/import/" + id))
                .andExpect(status().isOk());

        verify(personService, times(1)).importById(id);
    }

    @Test
    void importByIdConstraintViolationException() throws Exception {
        Long id = 84L;

        doThrow(ConstraintViolationException.class).when(personService).importById(id);
        mockMvc.perform(get(PersonController.BASE_URL + "/import/" + id))
                .andExpect(status().isConflict());

        verify(personService, times(1)).importById(id);
    }
}