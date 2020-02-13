package com.example.java.service;


import com.example.java.model.Messaggi;
import com.example.java.repository.MessaggiRepository;
import com.example.java.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service("messaggiService")
public class MessaggiServiceImpl implements MessaggiService{

    @Qualifier("messaggiRepository")
    @Autowired
    private MessaggiRepository messaggiRepository;

    @Override
    public void saveMessage(Messaggi messaggi,String sede_email,Integer user_id) {
        LocalDate localDate = LocalDate.now();
        int giorno = localDate.getDayOfMonth();
        Month mese = localDate.getMonth();
        int anno = localDate.getYear();
        String data = giorno+" "+mese+" "+anno;
        messaggi.setData(data);
        messaggi.setUser_id(user_id);
        messaggi.setSede_email(sede_email);
        messaggiRepository.save(messaggi);
    }

    @Override
    public List<Messaggi> retriveMessById(Integer user_id) {
        return messaggiRepository.findMessaggiByUserId(user_id);
    }
}
