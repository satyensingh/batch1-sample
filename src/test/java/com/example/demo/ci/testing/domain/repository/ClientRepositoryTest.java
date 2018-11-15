package com.example.demo.ci.testing.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.ci.domain.model.Client;
import com.example.demo.ci.domain.repository.ClientRepository;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testFindByName() {
        entityManager.persist(new Client("Foo"));

        Optional<Client> client = clientRepository.findByName("Foo");
        assertEquals("Foo", client.get().getName());
    }

}
