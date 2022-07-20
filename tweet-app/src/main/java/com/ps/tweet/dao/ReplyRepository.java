package com.ps.tweet.dao;

import com.ps.tweet.entities.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends CrudRepository<Reply, Integer> {
    @Query(nativeQuery = true, value = "select * from reply r where r.tweet_id = :tweet_id")
    List<Reply> findByTweet_id(Integer tweet_id);

}
