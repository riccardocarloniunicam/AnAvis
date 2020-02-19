package com.example.java.controller.Donatore;


import com.example.java.model.Analisi;
import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import com.example.java.service.AnalisiService;
import com.example.java.service.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private AnalisiService analisiService;
    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private User userr;


    @RequestMapping(value = {"/home/report"},method = RequestMethod.GET)
    public ModelAndView reportVisiteAnalisi(){
        ModelAndView modelAndView = new ModelAndView();
        List<Analisi> analisi = analisiService.findbyuserID(userr.getId());
        modelAndView.addObject("analisi",analisi);
        modelAndView.addObject("user",userr);
        modelAndView.setViewName("home/report");
        return modelAndView;

    }

}
