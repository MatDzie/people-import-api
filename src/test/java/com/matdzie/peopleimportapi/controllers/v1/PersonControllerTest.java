package com.matdzie.peopleimportapi.controllers.v1;

import com.matdzie.peopleimportapi.controllers.ControllerAdviceExceptionHandler;
import com.matdzie.peopleimportapi.services.PersonService;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(sut)
                .setControllerAdvice(new ControllerAdviceExceptionHandler())
                .build();
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