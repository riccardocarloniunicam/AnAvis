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

     List<User> findToApprove();


     User findByIdForApprovazione(Integer id);

     Integer UpgradeRole(Integer id); //upgrade role

     Integer UpgradeParamDonatore(Integer id); //upgrade parametro donatore nel database;


     Integer downGradeParamModulo(Integer id);

     List<User> findUserOfPrenotazione(String email);








}