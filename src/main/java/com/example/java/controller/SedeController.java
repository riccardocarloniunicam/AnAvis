package com.example.java.controller;


import com.example.java.model.*;
import com.example.java.service.NewsService;
import com.example.java.service.UserService;
import com.example.java.service.UtenteSediService;
import org.springframework.beans.factory.annotation.Autowired;
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






    @RequestMapping(value= {"/sede/home"}, method= RequestMethod.GET)
    public ModelAndView sede() {
        ModelAndView model = new ModelAndView();
        model.setViewName("sede/home");
        return model;
    }


    @RequestMapping(value = "/sede/inserisci-news",method = RequestMethod.GET)
    public ModelAndView inserisciNews(){
        ModelAndView model = new ModelAndView();
        News news = new News();
        model.addObject("news", news);
        model.setViewName("sede/inserisci-news");
        return model;
    }


    @RequestMapping(value= {"/sede/inserisci-news"}, method=RequestMethod.POST)
    public ModelAndView nuovoModulo(@Valid News news, BindingResult bindingResult) throws InterruptedException {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UtenteSedi utenteSedi = utenteSediService.findUserByEmail(auth.getName());

        newsService.saveNews(news,utenteSedi.getId(),utenteSedi.getEmail());
        model.addObject("msg", "News pubblicata correttamente");
        model.addObject("news", new News());
        model.setViewName("sede/inserisci-news");
    return model;
    }



}

