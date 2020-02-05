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
        prenotazioni.setStato("Prenotata");
        prenotazioniRepository.save(prenotazioni);
    }

    @Override
    public List<Prenotazioni> findByorarioedata(String data, String orario) {
        return prenotazioniRepository.findByOrarioAndData(data,orario);
    }
}
