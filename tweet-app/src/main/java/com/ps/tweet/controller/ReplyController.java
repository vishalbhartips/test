package com.ps.tweet.controller;

import com.ps.tweet.entities.Reply;
import com.ps.tweet.services.ReplyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {
    @Autowired
    ReplyServices replyServices;

    //
    @PostMapping("/post/reply/{tweet_id}/{reply_tweet}")
    public Reply postReply(@PathVariable("tweet_id") Integer tweet_id, @PathVariable("reply_tweet") Integer reply_tweet, @RequestBody Reply reply) {
        return replyServices.addReply(tweet_id, reply_tweet,reply);
    }

}
