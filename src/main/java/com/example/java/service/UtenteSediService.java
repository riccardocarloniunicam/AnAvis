package com.example.java.service;


import com.example.java.model.User;
import com.example.java.model.UtenteSedi;

import java.util.List;

public interface UtenteSediService {
    List<UtenteSedi> listAll();
    UtenteSedi findUserByEmail(String email);
}
