package com.example.java.service;


import com.example.java.model.UtenteSedi;
import com.example.java.repository.UserRepository;
import com.example.java.repository.UtenteSediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("utentesediService")
public class UtenteSediServiceImpl implements UtenteSediService {

    @Qualifier("utentesediRepository")
    @Autowired
    private UtenteSediRepository utenteSediRepository;


    @Override
    public List<UtenteSedi> listAll() {
        return utenteSediRepository.findAll();
    }


    @Override
    public UtenteSedi findUserByEmail(String email) {
        return utenteSediRepository.findByEmail(email);
    }
}


