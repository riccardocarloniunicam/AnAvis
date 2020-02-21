package com.example.java.service;

import com.example.java.model.Orario;
import com.example.java.repository.OrarioReposiotry;
import com.example.java.repository.PrenotazioniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("orarioService")
public class OrarioServiceImpl  implements  OrarioService{


    @Qualifier("orarioRepository")
    @Autowired
    private OrarioReposiotry orarioReposiotry;

    @Override
    public List<Orario> getORario(String sede_id, String data) {
        return orarioReposiotry.getOrario(sede_id,data);
    }
}
