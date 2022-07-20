package com.ps.tweet.services;

import com.ps.tweet.dao.FollowerRepository;
import com.ps.tweet.dao.UserRepository;
import com.ps.tweet.entities.Follower;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FollowerServicesTest {

    @Mock
    UserRepository userRepository;

    @Mock
    FollowerRepository followerRepository;

    FollowerServices followerServices;
    User user1, user2;
    Follower follower;

    @BeforeEach
    void setUp() {
        this.followerServices = new FollowerServices(followerRepository, userRepository);
        this.user1 = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        this.user2 = new User(2, "vishal", "bharti", "vishalbharti00000000@gmail.com", "vishal", null, "7004739018", 0);
        this.follower = new Follower(1,1,2);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    void addFollower() {
        followerServices.addFollower(user1.getUser_id(), follower);
        user1.setNumber_of_followers(1);
        when(userRepository.findById(user1.getUser_id())).thenReturn(Optional.ofNullable(user1));
        verify(followerRepository).save(follower);
        verify(userRepository).save(user1);

    }
}