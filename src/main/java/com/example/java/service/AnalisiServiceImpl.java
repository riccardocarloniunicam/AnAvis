package com.example.java.service;

import com.example.java.model.Analisi;
import com.example.java.repository.AnalisiRepository;
import com.example.java.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("analisiService")
public class AnalisiServiceImpl implements AnalisiService {

    @Qualifier("analisiRepository")
    @Autowired
    private AnalisiRepository analisiRepository;


    @Override
    public void saveAnalisi(Analisi analisi) {
         analisiRepository.save(analisi);
    }

    @Override
    public List<Analisi> findbyuserID(Integer user_id) {
        return  analisiRepository.findAanalisiByID(user_id);
    }
}
