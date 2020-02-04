package com.example.java.service;

import com.example.java.model.Sede;
import com.example.java.repository.SedeRepository;
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

@Service("sedeService")
public class SedeServiceImpl implements SedeService {

    @Qualifier("sedeRepository")
    @Autowired
    private SedeRepository sedeRepository;

    public List<Sede> listAll() {
        return sedeRepository.findAll();
    }




}