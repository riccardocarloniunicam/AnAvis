package com.example.java.service;

import com.example.java.model.Messaggi;

import java.util.List;

public interface MessaggiService {

    void saveMessage(Messaggi messaggi,String sede_email,Integer user_id);
    List<Messaggi> retriveMessById(Integer user_id); //retrive messaggi
}
