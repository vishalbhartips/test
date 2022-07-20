package com.ps.tweet.dao;

import com.ps.tweet.entities.Reply;
import com.ps.tweet.entities.Tweet;
import com.ps.tweet.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {
    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    User user;
    Tweet tweet;
    Tweet replyTweet;
    Reply reply;

    @BeforeEach
    public void setUp() {
        user = new User(1, "vishal", "bharti", "vishalbharti0000@gmail.com", "vishal", null, "7004739018", 0);
        user = userRepository.save(user);
        tweet = new Tweet(1, "content for testing", user.getUser_id(), 0, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        replyTweet = new Tweet(2, "reply content for testing", user.getUser_id(), 1, new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        tweet = tweetRepository.save(tweet);
        replyTweet = tweetRepository.save(replyTweet);
        reply = new Reply(1,tweet.getTweet_id(), replyTweet.getTweet_id());
        reply = replyRepository.save(reply);
    }
    @AfterEach
    void destroy() {
        replyRepository.deleteAll();
        tweetRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findByTweet_id() {
        List<Reply> replyList = replyRepository.findByTweet_id(tweet.getTweet_id());
        assertEquals(replyList.size(), 1);
        assertEquals(replyList.get(0).getReply_tweet(), replyTweet.getTweet_id());
    }
}