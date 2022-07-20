package com.ps.tweet.controller;

import com.ps.tweet.entities.Follower;
import com.ps.tweet.entities.User;
import com.ps.tweet.services.FollowerServices;
import com.ps.tweet.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UpdateProfile {
    @Autowired
    FollowerServices followerServices;

    @Autowired
    UserServices userServices;

    //Update user && Update password
    @PutMapping("/update/profile/{user_id}")
    public ResponseEntity<User> updateProfile(@PathVariable Integer user_id, @RequestBody User user) {
        User user1 = userServices.updateUser(user_id, user);
        if(user1 == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user1, HttpStatus.OK);
    }

    //Add followers
    @PostMapping("/add/follower/{user_id}/{follower_user_id}")
    public Follower addFollower(@PathVariable Integer user_id, @RequestBody Follower follower) {
        return followerServices.addFollower(user_id, follower);
    }

    //Add profile Pic
    @PutMapping("/add/profile/pic/{user_id}")
    User addProfilePic(@PathVariable Integer user_id, MultipartFile profile_pic) throws IOException {
        return userServices.addProfilePic(user_id, profile_pic);
    }

}