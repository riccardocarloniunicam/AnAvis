package com.example.java.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.java.model.Modulo;
import com.example.java.model.Prenotazioni;
import com.example.java.model.Sede;
import com.example.java.service.ModuloService;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.SedeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import com.example.java.model.User;
import com.example.java.service.UserService;

import java.util.List;


@Controller
public class UserController  {
    @Autowired
    private UserService userService;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private ModuloService moduloService;

private User userInfo;


    public User getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUserByEmail(auth.getName());
    }

    @RequestMapping(value= {"/sede/home"}, method=RequestMethod.GET)
    public ModelAndView sede() {
        ModelAndView model = new ModelAndView();
        model.setViewName("sede/home");
        return model;
    }

    @RequestMapping(value= {"/","/login"}, method=RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }

    @RequestMapping(value= {"/home/nuovo-modulo"}, method=RequestMethod.GET)
    public ModelAndView modulo() {
        ModelAndView model = new ModelAndView();
        Modulo modulo = new Modulo(); //vuota

        model.addObject("user",getUserInfo());
        model.addObject("modulo", modulo);
        model.setViewName("home/nuovo-modulo");
        return model;
    }


    public ModelAndView check(@Valid Modulo modulo, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        if (moduloService.moduloEsiste(getUserInfo().getId())){
            bindingResult.rejectValue("nome", "error.modulo", "Hai già inviato il modulo. Attendi la revisione degli amministratori per inviarne uno nuovo");
        }else
        if (!moduloService.checkInput(modulo)){
            bindingResult.rejectValue("residenza", "error.modulo", "Per Favore completa tutti i campi");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuovo-modulo");
        }else {
            moduloService.saveModulo(modulo,getUserInfo().getId());
            model.addObject("modulo", new Modulo());
            model.addObject("msg", "Prenotazione Effettuata con Successo!");
            model.setViewName("home/nuovo-modulo");

        }
        return model;

    }

    @RequestMapping(value= {"/home/nuovo-modulo"}, method=RequestMethod.POST)
    public ModelAndView nuovoModulo(@Valid Modulo modulo, BindingResult bindingResult) throws InterruptedException {

        return check(modulo,bindingResult);
    }

    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public ModelAndView createUser(@Valid User user, BindingResult bindingResult) throws InterruptedException {
        ModelAndView model = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/signup");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new User());
            model.setViewName("user/signup");
        }

        return model;
    }

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.addObject("user",getUserInfo());
        model.setViewName("home/home");
        return model;
    }


    //Prendo i dati della persona che chiama il metodo.
    @RequestMapping(value = "home/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request) {
        return "redirect:/home/profile/" + getUserInfo().getNome();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/profile/{name}", method = RequestMethod.GET)
    public String seeProfileTrue(@PathVariable(value="name")String personEmail,Model model, HttpServletRequest request) {
        model.addAttribute("user",getUserInfo());
        return "home/profile";
    }




    @RequestMapping(value= {"/home/nuova-analisi"}, method=RequestMethod.GET)
    public ModelAndView prenota() {
        ModelAndView model = new ModelAndView();
       Prenotazioni prenotazione = new Prenotazioni(); //vuota
       //
        List<Sede> sede = sedeService.listAll();
        model.addObject("user",getUserInfo());
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
        if (prenotazioniService.prenotazioneEffettuata(getUserInfo().getId())){
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
            prenotazioniService.savePrenotazione(prenotazioni,getUserInfo().getId(),getUserInfo().getNome(),getUserInfo().getCognome());
            model.addObject("prenotazioni", new Prenotazioni());
            model.addObject("msg", "Prenotazione Effettuata con Successo!");
            model.setViewName("home/nuova-analisi");
            System.out.println("Prenotazione Effettuata con Successo");

        }
            return model;
        }

    @RequestMapping(value = "home/prenotazioni", method = RequestMethod.GET)
    public String prenotazioni(Model model,HttpServletRequest request) {
        Prenotazioni prenotazioni = prenotazioniService.getPrenotazioni(getUserInfo().getId());
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
        return "redirect:/home/cancella-prenotazione/" + getUserInfo().getId();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/cancella-prenotazione/{id}", method = RequestMethod.GET)
    public String CancellaPrenotazioneid(@PathVariable(value="id")Integer  id,Model model, HttpServletRequest request) {
        prenotazioniService.delete(id);
        model.addAttribute("prenotazione","null");
        model.addAttribute("msg", "Prenotazione Cancellata con Successo!");
        return "home/prenotazioni";
    }


    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

