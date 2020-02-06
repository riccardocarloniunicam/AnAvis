package com.example.java.service;


import com.example.java.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

     User findUserByEmail(String email);
     void saveUser(User user);
     List<User> listAll();
     User get(Long id);
     void delete(Long id);
     Optional<User> findUserById(Long id);








}