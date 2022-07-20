package com.ps.tweet.services;

import com.ps.tweet.dao.ReplyRepository;
import com.ps.tweet.dao.TweetRepository;
import com.ps.tweet.dao.UserRepository;
import com.ps.tweet.entities.Reply;
import com.ps.tweet.entities.Tweet;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TweetServicesTest {

    @Mock
    TweetRepository tweetRepository;
    @Mock
    ReplyRepository replyRepository;
    @Mock
    UserRepository userRepository;

    TweetServices tweetServices;
    User user;
    Tweet tweet;
    Tweet replyTweet;

    @BeforeEach
    void setUp() {
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        this.tweetServices = new TweetServices(tweetRepository, replyRepository);
        tweet = new Tweet(1, "tweet content", 1, 0, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        replyTweet = new Tweet(2, "reply content for testing", 1, 1, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        userRepository.save(user);
    }

    @Test
    void addTweet() {
        tweetServices.addTweet(1, tweet);
        verify(tweetRepository).save(tweet);
    }

    @Test
    void getAllTweet() {
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet);
        when(tweetRepository.findByIsNot_reply(0)).thenReturn(tweets);
        tweetServices.getAllTweet();
        verify(tweetRepository).findByIsNot_reply(0);
    }

    @Test
    void getTweetById() {
        tweetServices.getTweetById(1);
        verify(tweetRepository).findById(1);
    }

    @Test
    void getAllReplyByTweetId() {
        when(replyRepository.findByTweet_id(1)).thenReturn(Arrays.asList(new Reply(1, 1, 2)));
        tweetServices.getAllReplyByTweetId(1);
        verify(tweetRepository).findById(2);
    }

    @Test
    void getAllTweetByUserId() {
        tweetServices.getAllTweetByUserId(1);
        verify(tweetRepository).findByUser_id(1);
    }
}