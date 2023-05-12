package com.pockett.userService.services;

import com.pockett.userService.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    //create user
    User saveUser(User user);

    //get all users


    List<User> getAllUsers();

    //single user

    User getUser(String userId);

    //todo

    //Update

    //delete


}
