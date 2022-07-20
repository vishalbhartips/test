package com.ps.tweet.services;

import com.ps.tweet.dao.ReplyRepository;
import com.ps.tweet.entities.Reply;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReplyServices {
    @Autowired
    ReplyRepository replyRepository;


    public Reply addReply(Integer tweet_id, Integer reply_tweet, Reply reply) {
        reply.setTweet_id(tweet_id);
        reply.setReply_tweet(reply_tweet);
        return replyRepository.save(reply);
    }
}
