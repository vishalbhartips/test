package com.ps.tweet.dao;

import com.ps.tweet.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void newUser() {
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);

        user = userRepository.save(user);
    }

    @AfterEach
    void destroy() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        Optional<User> userFromDB = userRepository.findByEmail("vishalbharti0000@gmail.com");
        assertEquals(user, userFromDB.get());
    }
}