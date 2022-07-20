package com.ps.tweet.services;

import com.ps.tweet.dao.FollowerRepository;
import com.ps.tweet.dao.UserRepository;
import com.ps.tweet.entities.Follower;
import com.ps.tweet.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@NoArgsConstructor
@AllArgsConstructor
@Service
public class FollowerServices {
    @Autowired
    FollowerRepository followerRepo;

    @Autowired
    UserRepository userRepository;



    public Follower addFollower(Integer user_id, Follower follower) {
        Follower follower1 = followerRepo.save(follower);
        Optional<User> optionalUser = userRepository.findById(user_id);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setNumber_of_followers(user.getNumber_of_followers() != null ? user.getNumber_of_followers() + 1 : 0);
            userRepository.save(user);
            return follower1;
        }
        return null;
    }

}
