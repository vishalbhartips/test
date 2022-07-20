package com.ps.tweet.services;

//
import com.ps.tweet.dao.UserRepository;
import com.ps.tweet.entities.LoginUser;
import com.ps.tweet.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//
import java.io.IOException;
import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

@NoArgsConstructor
@AllArgsConstructor
@Service
@PropertySource("classpath:application.properties")
public class UserServices {

    @Autowired
    UserRepository userRepo;

    @Value("${user.randomForLoopInHash}")
    Integer randomForLoopInHash;
    @Value(("${user.salt}"))
    String salt;



    public User addUser(User user) {
        user.setPassword(this.secureHashGenerator(user.getPassword(), salt));
        return userRepo.save(user);
    }

    public User login(LoginUser loginUser) {

        Optional<User> user = userRepo.findByEmail(loginUser.getEmail());
        if(user.isPresent()) {
            if (user.get().getPassword().equals(this.secureHashGenerator(loginUser.getPassword(), salt))) {
                return user.get();
            }
        }
        return null;
    }

    public User updateUser(Integer user_id, User user) {
        Optional<User> optionalUser = userRepo.findById(user_id);
        if(optionalUser.isPresent()) {
            User userFromDB = optionalUser.get();
            userFromDB.setEmail(user.getEmail() != null ? user.getEmail() : userFromDB.getEmail());
            userFromDB.setFirst_name(user.getFirst_name() != null ? user.getFirst_name() : userFromDB.getFirst_name());
            userFromDB.setLast_name(user.getLast_name() != null ? user.getLast_name() : userFromDB.getLast_name());
            userFromDB.setContact_number(user.getContact_number() != null ? user.getContact_number() : userFromDB.getContact_number());
            userFromDB.setPassword(user.getPassword() != null ? this.secureHashGenerator(user.getPassword(), salt) : userFromDB.getPassword());

            userFromDB.setProfile_pic(user.getProfile_pic() != null ? user.getProfile_pic() : userFromDB.getProfile_pic());

            return userRepo.save(userFromDB);
        }
        return null;
    }

    public User addProfilePic(Integer user_id, MultipartFile multipartFile) throws IOException {
        Optional<User> optionalUser = userRepo.findById(user_id);
        if(optionalUser.isPresent()) {
            User userFromDB = optionalUser.get();
            userFromDB.setProfile_pic(multipartFile.getBytes());
            return userRepo.save(userFromDB);
        }
        return null;
    }



    private String secureHashGenerator(String password, String salt){
        String hash = getHash(password, salt);
        for (int i = 0; i < randomForLoopInHash; i++){
            hash = getHash(password, hash);
        }
        return hash;
    }
    private String getHash(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
        return generatedPassword;
    }

}
