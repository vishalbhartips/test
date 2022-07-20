package com.ps.tweet.services;

import com.ps.tweet.dao.HashtagRepository;
import com.ps.tweet.entities.Hashtag;
import com.ps.tweet.entities.Tweet;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class HashtagServicesTest {
    @Mock
    HashtagRepository hashtagRepository;

    HashtagServices hashtagServices;
    Hashtag hashtag;
    Tweet tweet;
    User user;

    @BeforeEach
    void setUp() {
        this.hashtagServices = new HashtagServices(hashtagRepository);
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        tweet = new Tweet(1, "content for testing", user.getUser_id(), 0, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        hashtag = new Hashtag(1, 1, "#test");
    }

    @Test
    void addHashtag() {
        hashtagServices.addHashtag(1, hashtag);
        verify(hashtagRepository).save(hashtag);
    }
}