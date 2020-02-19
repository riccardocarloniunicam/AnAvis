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





    @RequestMapping(value= {"/home/news"}, method=RequestMethod.GET)
    public String news(){
        return "home/news";
    }





}
