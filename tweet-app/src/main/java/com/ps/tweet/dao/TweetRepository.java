package com.ps.tweet.dao;

import com.ps.tweet.entities.Tweet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Integer> {
    @Query(nativeQuery = true, value = "select * from tweet where is_reply=0 order by created desc")
    List<Tweet> findByIsNot_reply(Integer is_reply);

    @Query(nativeQuery = true, value = "select * from tweet t where t.user_id = :user_id and is_reply = 0")
    List<Optional<Tweet>> findByUser_id(Integer user_id);
}
