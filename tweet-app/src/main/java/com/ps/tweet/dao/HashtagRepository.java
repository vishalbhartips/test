package com.ps.tweet.dao;

import com.ps.tweet.entities.Hashtag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Integer> {
}
