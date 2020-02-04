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
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
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
        model.addObject("prenotazione", prenotazione);
        model.setViewName("home/nuova-analisi");

        return model;
    }

    @RequestMapping(value ={"/home/nuova-analisi"} , method = RequestMethod.POST)
    public ModelAndView confermaPrenotazione(@Valid Prenotazioni prenotazioni,BindingResult bindingResult) throws InterruptedException {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();


            prenotazioniService.savePrenotazione(prenotazioni);
            model.addObject("prenotazione", new Prenotazioni());
            model.setViewName("home/nuova-analisi");
            System.out.println("Prenotazione Effettuata con Successo");

        return  model;
    }






    /*
//NUOVA ANALISI GET
    @RequestMapping(value= {"/home/nuova-analisi"}, method=RequestMethod.GET)
    public String nuovaAnalsi(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Sede> sede = sedeService.listAll();
    //mando attributi per thymeleaf
        model.addAttribute("sede",sede);
        model.addAttribute("user",user);
        return "home/x";

    }

*/






















    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

