package com.example.java.controller.Sede;


import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import com.example.java.model.UtenteSedi;
import com.example.java.service.PrenotazioniService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PrenotazioniEseguiteSedeController {
    @Autowired
    private UtenteSedi utenteSedi;
    @Autowired
    private PrenotazioniService prenotazioniService;

    //UPLOAD FILE
    @RequestMapping(value = "/inserisci_analisi",method = RequestMethod.POST)
    public String getUser(@Valid User user,@Valid Prenotazioni prenotazioni, Model model, HttpServletRequest request){
        return "redirect:/sede/inserisci_analisi/user_id/" + prenotazioni.getUser_id() + "/"+prenotazioni.getId();
    }

//TODO:ADD FILE  IN TABLE,RETRIVE,UPDATE ANALISI ESEGUITE
    @RequestMapping(value = "/sede/inserisci_analisi/user_id/{user_id}/{prenotazione_id}",method = RequestMethod.GET)
    public String getUser(@PathVariable(value = "user_id")Integer user_id,@PathVariable(value = "prenotazione_id")Integer prenotazione_id, Model model, HttpServletRequest request){
        model.addAttribute("user_id",user_id);
        model.addAttribute("prenotazione_id",prenotazione_id);
        return "/sede/inserisci_analisi";
    }



    //FUNZIONE DI CERCA
    @RequestMapping(value = "/sede/prenotazioni-eseguite",method = RequestMethod.GET)
    public String prenotazioniEseguite(Model model, @RequestParam Optional<String> nome){
        List<Prenotazioni> prenotazionis = prenotazioniService.findLike(nome.orElse("_"),utenteSedi.getSede_id());
        model.addAttribute("prenotazione",prenotazionis);
        return "sede/prenotazioni-eseguite";
    }



}
