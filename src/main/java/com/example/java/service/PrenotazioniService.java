package com.example.java.service;

import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PrenotazioniService {
    List<Prenotazioni> listall();
    void savePrenotazione(Prenotazioni prenotazioni,Integer user_id,String nome,String cognome,String email);
    List<Prenotazioni> findByorarioedata(String data, String orario,Integer sede_id);

    Boolean checkPrenotazione(String data,String orario,Integer sede_id);

    Prenotazioni getPrenotazioni(Integer id);
    Prenotazioni getPrenotazionebyID(Integer id);

    Boolean prenotazioneEffettuata(Integer user_id);

    void delete(Integer id);
    //retrive appuntamenti prenotati
    List<Prenotazioni> appuntamentiInSede(String email);

}
