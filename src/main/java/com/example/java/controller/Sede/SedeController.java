package com.example.java.controller.Sede;


import com.example.java.model.*;
import com.example.java.service.NewsService;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.UserService;
import com.example.java.service.UtenteSediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SedeController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UtenteSediService utenteSediService;
    @Autowired
    private PrenotazioniService prenotazioniService;


    @Qualifier("utente_sede")
    @Autowired
    private UtenteSedi utenteSedi;



    @RequestMapping(value= {"/sede/home"}, method= RequestMethod.GET)
    public ModelAndView sede() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addObject("prenotazioni",prenotazioniService.getCountPrenotazioni(utenteSedi.getSede_id()));
        model.addObject("eseguite",prenotazioniService.retriveEseguite(utenteSedi.getSede_id()));
        model.addObject("dacaricare",prenotazioniService.analisiDaCaricare(utenteSedi.getSede_id()));
        model.setViewName("sede/home");
        return model;
    }




}

