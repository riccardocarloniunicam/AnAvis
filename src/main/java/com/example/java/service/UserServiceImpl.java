package com.example.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.java.model.Role;
import com.example.java.model.User;
import com.example.java.repository.RoleRepository;
import com.example.java.repository.UserRepository;

import java.util.*;


import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRespository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return  userRepository.findById(id);
    }

    @Override
    public void saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setDonatore(0);
        user.setEmergenza(0);
        user.setModulo(0);
        Role userRole = roleRespository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }


    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findToApprove() {
        return userRepository.findAllToApprove();
    }

    @Override
    public User findByIdForApprovazione(Integer id) {
        return userRepository.findTheUser(id);
    }

    @Override
    public Integer UpgradeRole(Integer id) {
        return userRepository.UpgradeDonatore(id);
    }

    @Override
    public Integer UpgradeParamDonatore(Integer id) {
        return userRepository.UppaParametroDonatore(id);
    }

    @Override
    public Integer downGradeParamModulo(Integer id) {
        return userRepository.DownGrandeParametroModulo(id);
    }

    @Override
    public List<User> findUserOfPrenotazione(String email) {
        return userRepository.retriveUserAppuntamento(email);

    }

    @Override
    public Integer findNumberToapprove() {
        return userRepository.findNumberToApprove();
    }


}