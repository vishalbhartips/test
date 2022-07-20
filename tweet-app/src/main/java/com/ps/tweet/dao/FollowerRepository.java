package com.ps.tweet.dao;

import com.ps.tweet.entities.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends CrudRepository<Follower, Integer> {
}
