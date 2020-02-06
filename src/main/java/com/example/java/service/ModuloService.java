package com.example.java.service;

import com.example.java.model.Modulo;
import com.example.java.model.Prenotazioni;


import java.util.List;

public interface ModuloService {
    void saveModulo(Modulo modulo,Integer id);
    List<Modulo> findbyid(Integer id);
    Integer putModuleParameter(Integer id);
    Boolean moduloEsiste(Integer id);
    Boolean checkInput(Modulo modulo);
}
