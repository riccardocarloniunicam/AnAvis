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
    public void savePrenotazione(Prenotazioni prenotazioni,Integer user_id,String nome,String cognome) {
        prenotazioni.setUser_id(user_id);
        prenotazioni.setNome(nome);
        prenotazioni.setCognome(cognome);
        prenotazioni.setStato("Prenotata");
        prenotazioniRepository.save(prenotazioni);
    }

    @Override
    public List<Prenotazioni> findByorarioedata(String data, String orario,Integer sede_id) {
        return prenotazioniRepository.findByOrarioAndData(data,orario,sede_id);
    }

    @Override
    public Boolean checkPrenotazione(String data, String orario, Integer sede_id) {
        if (prenotazioniRepository.prenotazioneExist(data,orario,sede_id) == 1){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public Prenotazioni getPrenotazioni(Integer id) {
        return  prenotazioniRepository.findPrenotazioneByUserID(id);
    }

    @Override
    public Prenotazioni getPrenotazionebyID(Integer id) {
        return prenotazioniRepository.findPrenotazioneById(id);
    }

    @Override
    public Boolean prenotazioneEffettuata(Integer user_id) {
        if (prenotazioniRepository.prenotazioneGiaEffettuata(user_id) == 1){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public void delete(Integer id) {
        prenotazioniRepository.deletePrenotazione(id);
    }
}
