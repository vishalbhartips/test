package com.ps.tweet.dao;

import com.ps.tweet.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
//    @Query("select * from User u where u.email = :email")
//    User findByEmail(@Param("email") String email);
    Optional<User> findByEmail(String email);
}
