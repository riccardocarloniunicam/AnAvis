package com.example.java.service;

import com.example.java.model.DBFile;
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
    public void savePrenotazione(Prenotazioni prenotazioni,Integer user_id,String nome,String cognome,String email) {
        prenotazioni.setEmail(email);
        prenotazioni.setUser_id(user_id);
        prenotazioni.setNome(nome);
        prenotazioni.setCognome(cognome);
        prenotazioni.setStato("PRENOTATA");
        prenotazioni.setAnalisi(0);
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

    @Override
    public List<Prenotazioni> appuntamentiInSede(String email) {
        return prenotazioniRepository.retriveAppuntamentiPerSede(email);
    }

    @Override
    public Integer getCountPrenotazioni(Integer sede_id) {
        return prenotazioniRepository.retrivePrenotazioniCount(sede_id);
    }

    @Override
    public Integer updateState(Integer id) {
        return prenotazioniRepository.updateStato(id);
    }

    @Override
    public Integer retriveEseguite(Integer id) {
        return prenotazioniRepository.retrivePrenotazioniCountEseguite(id);
    }

    @Override
    public List<Prenotazioni> appuntamentiInSedeEseguiti(String email) {
        return prenotazioniRepository.retriveAppuntamentiPerSedeEseguiti(email);
    }

    @Override
    public Integer analisiDaCaricare(Integer id) {
        return prenotazioniRepository.analisiDaCaricare(id);
    }

    @Override
    public List<Prenotazioni> findLike(String email,Integer sede_id) {
        return prenotazioniRepository.findByNameLike(email,sede_id);
    }

    @Override
    public List<Prenotazioni> listAll() {
        return prenotazioniRepository.findAll();
    }

    @Override
    public List<Prenotazioni> findByNome(String nome) {
        return prenotazioniRepository.findByNome(nome);
    }

    @Override
    public Integer updateAnalisiParamentro(Integer id) {
        return prenotazioniRepository.updateParametroAnalisi(id);
    }

    @Override
    public List<Prenotazioni> report(Integer user_id) {
        return prenotazioniRepository.report(user_id);
    }


}
