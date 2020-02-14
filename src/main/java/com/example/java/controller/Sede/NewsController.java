package com.example.java.controller.Sede;


import com.example.java.model.News;
import com.example.java.model.UtenteSedi;
import com.example.java.service.NewsService;
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

@Controller
public class NewsController {

    @Autowired
    private UtenteSediService utenteSediService;
    @Autowired
    private NewsService newsService;

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
