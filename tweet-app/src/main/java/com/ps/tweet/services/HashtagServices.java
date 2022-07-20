package com.ps.tweet.services;

import com.ps.tweet.dao.HashtagRepository;
import com.ps.tweet.entities.Hashtag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class HashtagServices {
    @Autowired
    HashtagRepository hashtagRepository;


    public Hashtag addHashtag(Integer tweet_id, Hashtag hashtag) {
        hashtag.setTweet_id(tweet_id);
        return hashtagRepository.save(hashtag);
    }
}
