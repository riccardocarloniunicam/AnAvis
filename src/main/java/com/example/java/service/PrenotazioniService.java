package com.example.java.service;

import com.example.java.model.DBFile;
import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PrenotazioniService {
    List<Prenotazioni> listall();
    void savePrenotazione(Prenotazioni prenotazioni,Integer user_id,String nome,String cognome,String email);
    List<Prenotazioni> findByorarioedata(String data, String orario,Integer sede_id);
    List<Prenotazioni> listAll();
    List<Prenotazioni> findByNome(String nome);
    Boolean checkPrenotazione(String data,String orario,Integer sede_id);

    Prenotazioni getPrenotazioni(Integer id);
    Prenotazioni getPrenotazionebyID(Integer id);

    Boolean prenotazioneEffettuata(Integer user_id);

    void delete(Integer id);
    //retrive appuntamenti prenotati
    List<Prenotazioni> appuntamentiInSede(String email);

    List<Prenotazioni> appuntamentiInSedeEseguiti(String email); //Retrive appuntamenti in sede eseguiti

    //retrive prenotazioni nella sede con stato "prenotata"
    Integer getCountPrenotazioni(Integer sede_id);

    Integer updateState(Integer id);
    Integer retriveEseguite(Integer id); //Analisi eseguite
    Integer analisiDaCaricare(Integer id); //Analisi da caricare

    List<Prenotazioni> findLike(String email,Integer sede_id); //FindLike per il cerca

    Integer updateAnalisiParamentro(Integer id); //mette analisi a 1

    List<Prenotazioni> report(Integer user_id); //report


}
