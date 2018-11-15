package com.example.demo.ci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ci.domain.model.Account;
import com.example.demo.ci.domain.model.Client;
import com.example.demo.ci.domain.repository.AccountRepository;
import com.example.demo.ci.domain.repository.ClientRepository;
import com.example.demo.ci.service.exception.InvalidAccountCreationException;

@Service
public class AccountService {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String clientNumber) {
        Client client = clientRepository.findByNumber(clientNumber)
            .orElse(clientRepository.save(new Client(clientNumber)));

        if (accountRepository.findByClient(client).isPresent()) {
            throw new InvalidAccountCreationException("Client " + client.getNumber() + " already has an account");
        }

        return accountRepository.save(new Account(client));
    }

}
