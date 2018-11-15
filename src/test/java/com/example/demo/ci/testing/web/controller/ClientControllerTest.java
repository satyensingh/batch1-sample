package com.example.demo.ci.testing.web.controller;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.ci.domain.model.Client;
import com.example.demo.ci.service.CreateClientService;
import com.example.demo.ci.service.exception.ClientNameAlreadyExistsException;
import com.example.demo.ci.web.controller.ClientController;
import com.example.demo.ci.web.to.CreateClientRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CreateClientService createClientServiceMock;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testCreateClientSuccessfully() throws Exception {
        given(createClientServiceMock.createClient("Foo")).willReturn(new Client("Foo"));

        mockMvc.perform(post("/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Foo")))
                .andExpect(jsonPath("$.number", notNullValue()));
    }

    @Test
    public void testCreateClientWithEmptyName() throws Exception {
        mockMvc.perform(post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(new CreateClientRequest(""))))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateClientWithExistingName() throws Exception {
        given(createClientServiceMock.createClient("Foo")).willThrow(new ClientNameAlreadyExistsException());

        mockMvc.perform(post("/clients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(new CreateClientRequest("Foo"))))
            .andExpect(status().isConflict());
    }
}
