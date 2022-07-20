package com.ps.tweet.services;

import com.ps.tweet.dao.UserRepository;
import com.ps.tweet.entities.LoginUser;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    UserRepository userRepository;

    UserServices userServices;

    User user;

    @BeforeEach
    void setUp() {
        this.userServices = new UserServices(this.userRepository, 1, "bgmi");
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);

    }
    @AfterEach
    void destroy() {

    }

    @Test
    void addUser() {
        userServices.addUser(user);
        verify(userRepository).save(user);
    }

    @Test
    void login() {
        userServices.addUser(user);
        LoginUser loginUser = new LoginUser("vishalbharti0000@gmail.com", "vishal");
        userServices.login(loginUser);
        verify(userRepository).findByEmail(loginUser.getEmail());
    }

    @Test
    void updateUser() {
        userServices.addUser(user);
        userServices.updateUser(user.getUser_id(), user);
        verify(userRepository).save(user);
    }
}