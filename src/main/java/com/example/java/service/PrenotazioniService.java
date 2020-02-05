package com.example.java.service;

import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PrenotazioniService {
    List<Prenotazioni> listall();
    void savePrenotazione(Prenotazioni prenotazioni);
    List<Prenotazioni> findByorarioedata(String data, String orario);




}
