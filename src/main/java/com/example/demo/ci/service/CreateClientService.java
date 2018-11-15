package com.example.demo.ci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.ci.domain.model.Client;
import com.example.demo.ci.domain.repository.ClientRepository;
import com.example.demo.ci.service.exception.ClientNameAlreadyExistsException;
import com.example.demo.ci.service.exception.InvalidClientNameException;

@Service
public class CreateClientService {

    private ClientRepository clientRepository;

    @Autowired
    public CreateClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(String name) {
        if(StringUtils.isEmpty(name)) {
            throw new InvalidClientNameException();
        }

        if (clientRepository.findByName(name).isPresent()) {
            throw new ClientNameAlreadyExistsException();
        }

        return clientRepository.save(new Client(name));
    }
}
