package com.example.java.service;


import com.example.java.model.Orario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orarioService")
public interface OrarioService {

    List<Orario>  getORario(String sede_id,String data);
}
