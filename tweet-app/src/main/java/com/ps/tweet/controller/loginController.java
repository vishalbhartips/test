package com.ps.tweet.controller;

import com.ps.tweet.entities.LoginUser;
import com.ps.tweet.entities.User;
import com.ps.tweet.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class loginController {
    @Autowired
    UserServices services;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return services.addUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginUser loginUser) {
        User user = services.login(loginUser);
        if(user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
        }
    }

/*
{
"first_name" :"Vishal",
"last_name":"Bharti",
"email":"vishalbharti0000@gmail.com",
"password":"vishal",
"profile_pic":null,
"contact_number":"7004839018",
"number_of_followers":0
}
{
"first_name" :"Vivek",
"last_name":"Rai",
"email":"vivek0000@gmail.com",
"password":"vivek",
"profile_pic":null,
"contact_number":"5765875787",
"number_of_followers":0
}

{
"email":"vishal",
"password" :"vishal"
}
*/

}
