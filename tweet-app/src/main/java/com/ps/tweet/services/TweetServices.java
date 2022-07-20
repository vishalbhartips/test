package com.ps.tweet.services;

import com.ps.tweet.dao.ReplyRepository;
import com.ps.tweet.dao.TweetRepository;
import com.ps.tweet.entities.Reply;
import com.ps.tweet.entities.Tweet;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TweetServices {

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    ReplyRepository replyRepository;


    public Tweet addTweet(Integer user_id, Tweet tweet) {
        tweet.setUser_id(user_id);
        tweet.setCreated(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getAllTweet() {
        return tweetRepository.findByIsNot_reply(0);
    }

    public Optional<Tweet> getTweetById(Integer tweet_id) {
        return tweetRepository.findById(tweet_id);
    }

    public List<Optional<Tweet>> getAllReplyByTweetId(Integer tweet_id) {
        List<Reply> listOfReply = replyRepository.findByTweet_id(tweet_id);
        List<Optional<Tweet>> replyTweets = new ArrayList<>();
        for (int i = 0; i < listOfReply.size(); i++) {
            replyTweets.add(tweetRepository.findById(listOfReply.get(i).getReply_tweet()));
        }
        return replyTweets;
    }

    public List<Optional<Tweet>> getAllTweetByUserId(Integer user_id) {
        return tweetRepository.findByUser_id(user_id);
    }
}
