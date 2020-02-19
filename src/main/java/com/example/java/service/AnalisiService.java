package com.example.java.service;


import com.example.java.model.Analisi;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnalisiService {

    void saveAnalisi(Analisi analisi);
    List<Analisi> findbyuserID(Integer user_id);
}
