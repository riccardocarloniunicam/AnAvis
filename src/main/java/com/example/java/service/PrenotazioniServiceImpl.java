package com.example.java.service;

import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import com.example.java.repository.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("prenotazioniService")
public class PrenotazioniServiceImpl implements PrenotazioniService {

    @Qualifier("prenotazioniRepository")
    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    @Override
    public List<Prenotazioni> listall() {
        return prenotazioniRepository.findAll();
    }

    @Override
    public void savePrenotazione(Prenotazioni prenotazioni) {
        prenotazioni.setUser_id(39);
        prenotazioniRepository.save(prenotazioni);
    }
}
