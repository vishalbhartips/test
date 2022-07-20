package com.ps.tweet.dao;

import com.ps.tweet.entities.Tweet;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TweetRepositoryTest {
    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    UserRepository userRepository;

    User user;
    Tweet tweet;
    Tweet replyTweet;

    @BeforeEach
    public void setUp() {
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        user = userRepository.save(user);
        tweet = new Tweet(1, "content for testing", user.getUser_id(), 0, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        replyTweet = new Tweet(2, "reply content for testing", user.getUser_id(), 1, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        tweet = tweetRepository.save(tweet);
        replyTweet = tweetRepository.save(replyTweet);
    }
    @AfterEach
    public void destroy() {
        tweetRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByIsNot_reply() {
        List<Tweet> tweets = tweetRepository.findByIsNot_reply(0);
        assertEquals(tweets.get(0).getTweet_id(), tweet.getTweet_id());
        assertEquals(tweets.size(), 1);
    }

    @Test
    void findByUser_id() {
        List<Optional<Tweet>> tweets = tweetRepository.findByUser_id(user.getUser_id());
        assertEquals(tweets.size(), 1);
        assertEquals(tweets.get(0).get().getTweet_id(), tweet.getTweet_id());
    }
}