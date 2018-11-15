package com.example.demo.ci.testing.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.example.demo.ci.domain.model.Client;
import com.example.demo.ci.domain.repository.ClientRepository;
import com.example.demo.ci.service.CreateClientService;
import com.example.demo.ci.service.exception.ClientNameAlreadyExistsException;
import com.example.demo.ci.service.exception.InvalidClientNameException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class CreateClientServiceTest {

    private CreateClientService createClientService;
    private ClientRepository clientRepositoryMock;

    @Before
    public void setUp() {
        clientRepositoryMock = Mockito.mock(ClientRepository.class);
        createClientService = new CreateClientService(clientRepositoryMock);
    }

    @Test
    public void createClientSuccessfuly() throws Exception {
        when(clientRepositoryMock.findByName(eq("Foo"))).thenReturn(Optional.empty());
        doAnswer(returnsFirstArg()).when(clientRepositoryMock).save(any(Client.class));

        Client client = createClientService.createClient("Foo");
        assertEquals("Foo", client.getName());
        assertNotNull(client.getNumber());
    }

    @Test(expected = InvalidClientNameException.class)
    public void createClientWithEmptyName() throws Exception {
        createClientService.createClient("");
    }

    @Test(expected = ClientNameAlreadyExistsException.class)
    public void createClientWithExistingName() throws Exception {
        doThrow(new ClientNameAlreadyExistsException()).when(clientRepositoryMock).findByName(eq("Foo"));

        createClientService.createClient("Foo");
    }
}