package com.example.java.controller.Sede;


import com.example.java.model.Prenotazioni;
import com.example.java.model.UtenteSedi;
import com.example.java.service.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class PrenotazioniEseguiteSedeController {
    @Autowired
    private UtenteSedi utenteSedi;
    @Autowired
    private PrenotazioniService prenotazioniService;

//FUNZIONE DI CERCA
    @RequestMapping(value = "/sede/prenotazioni-eseguite",method = RequestMethod.GET)
    public String prenotazioniEseguite(Model model, @RequestParam Optional<String> nome){
        List<Prenotazioni> prenotazionis = prenotazioniService.findLike(nome.orElse("_"),utenteSedi.getSede_id());
        model.addAttribute("prenotazione",prenotazionis);
        return "sede/prenotazioni-eseguite";
    }



}
