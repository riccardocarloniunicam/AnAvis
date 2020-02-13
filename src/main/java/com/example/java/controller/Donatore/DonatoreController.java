package com.example.java.controller.Donatore;


import com.example.java.model.*;
import com.example.java.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class DonatoreController {

    @Autowired
    private UserService userService;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private User userr;

    @Autowired
    private NewsService newsService;

    @Autowired
    private MessaggiService messaggiService;

    @RequestMapping(value = {"/getmessaggi"},method = RequestMethod.GET)
    public ResponseEntity<Object> getMessaggi(){
        User user = userService.findUserByEmail(userr.getEmail());
        List<Messaggi> messaggis = messaggiService.retriveMessById(user.getId());
        return new ResponseEntity<>(messaggis, HttpStatus.OK);

    }

    @RequestMapping(value = {"/getnews"},method = RequestMethod.GET)
    public ResponseEntity<Object> getNews(){
        List<News> listaNews = newsService.listall();
        return new ResponseEntity<>(listaNews, HttpStatus.OK);

    }

    @RequestMapping(value = "home/prenotazioni", method = RequestMethod.GET)
    public String prenotazioni(Model model,HttpServletRequest request) {
        Prenotazioni prenotazioni = prenotazioniService.getPrenotazioni(userr.getId());
        if (prenotazioni==null){
            model.addAttribute("prenotazione","null");
        }else {
            model.addAttribute("prenotazione",prenotazioniService.getPrenotazionebyID(prenotazioni.getId()));
            model.addAttribute("sede",sedeService.getSede(prenotazioni.getSede_id()));

        }
        return "home/prenotazioni";

    }


    @RequestMapping(value = "home/cancella-prenotazione", method = RequestMethod.GET)
    public String cancellaPrenotazione(Model model, HttpServletRequest request) {
        return "redirect:/home/cancella-prenotazione/" + userr.getId();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/cancella-prenotazione/{id}", method = RequestMethod.GET)
    public String CancellaPrenotazioneid(@PathVariable(value="id")Integer  id,Model model, HttpServletRequest request) {
        prenotazioniService.delete(id);
        model.addAttribute("prenotazione","null");
        model.addAttribute("msg", "Prenotazione Cancellata con Successo!");
        return "home/prenotazioni";
    }


    @RequestMapping(value= {"/home/nuova-analisi"}, method=RequestMethod.GET)
    public ModelAndView prenota() {
        ModelAndView model = new ModelAndView();
        Prenotazioni prenotazione = new Prenotazioni(); //vuota
        //
        List<Sede> sede = sedeService.listAll();
        model.addObject("user",userr);
        model.addObject("sede",sede);
        //
        model.addObject("prenotazioni", prenotazione);
        model.setViewName("home/nuova-analisi");

        return model;
    }




    //PRENOTAZIONI POST
    @RequestMapping(value ={"/home/nuova-analisi"} , method = RequestMethod.POST)
    public ModelAndView confermaPrenotazione(@Valid Prenotazioni prenotazioni,BindingResult bindingResult) throws InterruptedException {
        ModelAndView model = new ModelAndView();
        if (prenotazioniService.prenotazioneEffettuata(userr.getId())){
            bindingResult.rejectValue("user_id", "error.prenotazioni", "Hai raggiunto il numero massimo di prenotazioni");
        }else
        if (prenotazioni.getData() == null || prenotazioni.getData().equals("")){
            bindingResult.rejectValue("data", "error.prenotazioni", "La data non può essere vuota");
        }

        if (prenotazioniService.checkPrenotazione(prenotazioni.getData(),prenotazioni.getOrario(),prenotazioni.getSede_id())){
            bindingResult.rejectValue("orario", "error.prenotazioni", "Orario e data per l'appuntamento sono occupato! Scegli un altra ora");
        }

        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuova-analisi");
        }else {
            prenotazioniService.savePrenotazione(prenotazioni,userr.getId(),userr.getNome(),userr.getCognome(),userr.getEmail());
            model.addObject("prenotazioni", new Prenotazioni());
            model.addObject("msg", "Prenotazione Effettuata con Successo!");
            model.setViewName("home/nuova-analisi");
            System.out.println("Prenotazione Effettuata con Successo");

        }
        return model;
    }

    @RequestMapping(value= {"/home/news"}, method=RequestMethod.GET)
    public String news(){
        return "home/news";
    }



}
