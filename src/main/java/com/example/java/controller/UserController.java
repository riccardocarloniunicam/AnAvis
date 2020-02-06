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
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addObject("user",userService.findUserByEmail(auth.getName()));
        model.addObject("modulo", modulo);
        model.setViewName("home/nuovo-modulo");
        return model;
    }


    public ModelAndView check(@Valid Modulo modulo, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if (moduloService.moduloEsiste(user.getId())){
            bindingResult.rejectValue("nome", "error.modulo", "Hai già inviato il modulo. Attendi la revisione degli amministratori per inviarne uno nuovo");
        }else
        if (!moduloService.checkInput(modulo)){
            bindingResult.rejectValue("residenza", "error.modulo", "Per Favore completa tutti i campi");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuovo-modulo");
        }else {
            moduloService.saveModulo(modulo,user.getId());
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

    //Implementare vmc
    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("user",user);
        model.setViewName("home/home");
        return model;
    }


    //Prendo i dati della persona che chiama il metodo.
    @RequestMapping(value = "home/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User author = userService.findUserByEmail(auth.getName());
        return "redirect:/home/profile/" + author.getEmail();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/profile/{personEmail}", method = RequestMethod.GET)
    public String seeProfileTrue(@PathVariable(value="personEmail")String personEmail,Model model, HttpServletRequest request) {
        model.addAttribute("user",userService.findUserByEmail(personEmail));
        return "home/profile";
    }




    @RequestMapping(value= {"/home/nuova-analisi"}, method=RequestMethod.GET)
    public ModelAndView prenota() {
        ModelAndView model = new ModelAndView();
       Prenotazioni prenotazione = new Prenotazioni(); //vuota
       //
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.findUserByEmail(auth.getName());
        List<Sede> sede = sedeService.listAll();
        model.addObject("user",user);
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
        if (prenotazioni.getData() == null || prenotazioni.getData().equals("")){
            bindingResult.rejectValue("data", "error.prenotazioni", "La data non può essere vuota");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if (prenotazioniService.checkPrenotazione(prenotazioni.getData(),prenotazioni.getOrario(),prenotazioni.getSede_id())){
            bindingResult.rejectValue("orario", "error.prenotazioni", "Orario e data per l'appuntamento sono occupato! Scegli un altra ora");
        }

        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuova-analisi");
        }else {
            prenotazioniService.savePrenotazione(prenotazioni,user.getId(),user.getNome(),user.getCognome());
            model.addObject("prenotazioni", new Prenotazioni());
            model.addObject("msg", "Prenotazione Effettuata con Successo!");
            model.setViewName("home/nuova-analisi");
            System.out.println("Prenotazione Effettuata con Successo");

        }
            return model;
        }
    //PRENOTAZIONI GET
    @RequestMapping(value = {"/home/prenotazioni"},method = RequestMethod.GET)
    public ModelAndView prenotazioni(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("home/prenotazioni");
        return modelAndView;
    }











    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

