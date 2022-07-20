package com.ps.tweet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Blob;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer user_id;

    String first_name;
    String last_name;
    String email;
    String password;
    @Lob
    byte[] profile_pic;
    String contact_number;
    Integer number_of_followers;
}
