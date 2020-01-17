package com.example.java.service;


import com.example.java.model.User;



public interface UserService {

    public User findUserByEmail(String email);
    public void saveUser(User user);

}