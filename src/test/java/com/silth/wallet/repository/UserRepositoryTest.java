package com.silth.wallet.repository;

import com.silth.wallet.entity.User;
import jdk.nashorn.internal.runtime.options.Option;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static final String EMAIL = "email@tste.com";

    @Autowired
    UserRepository repository;

    @Before
    public void setup(){
        User u = new User();
        u.setName("Setup user");
        u.setPassword("123");
        u.setEmail(EMAIL);

        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }

    @Test
    public void testSave(){
        User u = new User();
        u.setName("Teste");
        u.setPassword("123");
        u.setEmail("teste@teste.com");

        User response = repository.save(u);

        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = repository.findByEmailEquals(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}