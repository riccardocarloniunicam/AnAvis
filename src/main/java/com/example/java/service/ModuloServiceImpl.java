package com.example.java.service;


import com.example.java.model.Modulo;
import com.example.java.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("moduloService")
public class ModuloServiceImpl implements  ModuloService{

    @Qualifier("moduloRepository")
    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public void saveModulo(Modulo modulo, Integer id) {
        modulo.setUser_id(id);
        putModuleParameter(id);
        moduloRepository.save(modulo);
    }

    @Override
    public List<Modulo> findbyid(Integer id) {
        return moduloRepository.findByUserId(id);
    }

    @Override
    public Integer putModuleParameter(Integer id) {
        return moduloRepository.putModuleParameter(id);
    }

    @Override
    public Boolean moduloEsiste(Integer id) {
        if (moduloRepository.ModuleExist(id) == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean checkInput(Modulo modulo) {
        if (modulo.getNome().equals("") &&
                modulo.getLuogo_nascita().equals("") ||
                modulo.getData_nascita().equals("")  ||
                modulo.getResidenza().equals("")   ||
                modulo.getIndirzzo().equals("") ||
                modulo.getProfessione().equals("") ||
                modulo.getTellavoro().equals("")||
                modulo.getTelcasa().equals("")||
                modulo.getCellulare().equals("")||
                modulo.getEmail().equals("")){
                return false;
        }else{
            return true;
        }
    }
}
