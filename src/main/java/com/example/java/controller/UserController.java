package com.example.java.controller;


import javax.validation.Valid;

import com.example.java.model.Prenotazioni;
import com.example.java.model.Sede;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value= {"/"}, method=RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView model = new ModelAndView();

        model.setViewName("user/login");
        return model;
    }


    @RequestMapping(value= {"/login"}, method=RequestMethod.GET)
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
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("user",user);
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"home/profile"}, method=RequestMethod.GET) //Serve mvc
    public ModelAndView viewProfile(){
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        model.addObject("user",user);
        model.setViewName("home/profile");
        return model;

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

    @RequestMapping(value ={"/home/nuova-analisi"} , method = RequestMethod.POST)
    public ModelAndView confermaPrenotazione(@Valid Prenotazioni prenotazioni,BindingResult bindingResult) throws InterruptedException {
        ModelAndView model = new ModelAndView();
        List<Prenotazioni> pexist = prenotazioniService.findByorarioedata(prenotazioni.getData(),prenotazioni.getOrario());
        for (int i = 0; i<pexist.size();i++){
            System.out.println(pexist.get(i).getNome());
            if (pexist.get(i).getData().equals(prenotazioni.getData()) && pexist.get(i).getOrario().equals(prenotazioni.getOrario())){
                bindingResult.rejectValue("orario", "error.prenotazioni", "Orario e data per l'appuntamento sono occupato! Scegli un altra ora");
            }
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuova-analisi");
        }else {
            prenotazioniService.savePrenotazione(prenotazioni);
            model.addObject("prenotazioni", new Prenotazioni());
            model.addObject("msg", "Prenotazione Effettuata con Successo!");
            model.setViewName("home/nuova-analisi");
            System.out.println("Prenotazione Effettuata con Successo");

        }
            return model;
        }




    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

