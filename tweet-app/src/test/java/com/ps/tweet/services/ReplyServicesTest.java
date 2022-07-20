package com.ps.tweet.services;

import com.ps.tweet.dao.ReplyRepository;
import com.ps.tweet.entities.Reply;
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
class ReplyServicesTest {
    @Mock
    ReplyRepository replyRepository;

    ReplyServices replyServices;
    Tweet tweet;
    Tweet replyTweet;
    User user;
    Reply reply;

    @BeforeEach
    void setUp() {
        this.replyServices = new ReplyServices(replyRepository);
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        tweet = new Tweet(1, "content for testing", user.getUser_id(), 0, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        replyTweet = new Tweet(1, "content for testing", user.getUser_id(), 1, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        reply = new Reply(1, 1, 2);
    }

    @Test
    void addReply() {
        replyServices.addReply(1, 2, reply);
        verify(replyRepository).save(reply);
    }
}