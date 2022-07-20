package com.ps.tweet.controller;

import com.ps.tweet.entities.Tweet;
import com.ps.tweet.services.TweetServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class TweetController {
    @Autowired
    TweetServices services;

    //Add tweet with user id and then call hashtagController for adding hashtag
    @PostMapping("/post/tweet/{user_id}")
    public Tweet postTweet(@PathVariable Integer user_id, @RequestBody Tweet tweet) throws IOException {
        return services.addTweet(user_id, tweet);
    }

    //get all tweet in descending order of their creation
    @GetMapping("/get/all/tweet")
    public List<Tweet> getAllTweet() {
        return services.getAllTweet();
    }

    //get all tweet by tweet_id
    @GetMapping("get/tweet/{tweet_id}")
    public Optional<Tweet> getTweetById(@PathVariable Integer tweet_id) {
        return services.getTweetById(tweet_id);
    }

    //get all reply tweet of specified tweet_id
    @GetMapping("/get/reply/{tweet_id}")
    public List<Optional<Tweet>> getAllReplyByTweetId(@PathVariable Integer tweet_id) {
        return services.getAllReplyByTweetId(tweet_id);
    }

    @GetMapping("/get/tweetByUserId/{user_id}")
    public List<Optional<Tweet>> getAllTweetByUserId(@PathVariable Integer user_id) {
        return services.getAllTweetByUserId(user_id);
    }
}
