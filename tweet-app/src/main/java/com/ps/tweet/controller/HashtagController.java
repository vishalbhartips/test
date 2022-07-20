package com.ps.tweet.controller;

import com.ps.tweet.entities.Hashtag;
import com.ps.tweet.services.HashtagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HashtagController {
    @Autowired
    HashtagServices hashtagServices;

    @PostMapping("/add/hashtag/{tweet_id}")
    public Hashtag addHashtag(@PathVariable Integer tweet_id, @RequestBody Hashtag hashtag) {
        return hashtagServices.addHashtag(tweet_id, hashtag);
    }
}