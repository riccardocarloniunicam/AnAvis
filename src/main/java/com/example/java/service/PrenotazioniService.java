package com.example.java.service;

import com.example.java.model.Prenotazioni;
import com.example.java.model.User;

import java.util.List;

public interface PrenotazioniService {
    List<Prenotazioni> listall();
    void savePrenotazione(Prenotazioni prenotazioni);
}
