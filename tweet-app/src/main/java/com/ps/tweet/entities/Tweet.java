package com.ps.tweet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer tweet_id;

    String content;
    Integer user_id;  //user_id of User
    Integer is_reply;  //1 for replied tweet and 0 for normal tweet
    Timestamp created;
}
