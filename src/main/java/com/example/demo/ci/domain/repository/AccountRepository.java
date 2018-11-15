package com.example.demo.ci.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.ci.domain.model.Account;
import com.example.demo.ci.domain.model.Client;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByClient(Client client);

}
