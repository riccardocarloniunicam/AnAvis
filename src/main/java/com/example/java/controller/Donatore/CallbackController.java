package com.example.java.controller.Donatore;


import com.example.java.model.*;
import com.example.java.repository.DBFileRepository;
import com.example.java.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CallbackController {

    @Autowired
    private User userr;
@Autowired
private UserService userService;

@Autowired
private MessaggiService messaggiService;
@Autowired
private NewsService newsService;
@Autowired
    private SedeService sedeService;
@Autowired
private OrarioService orarioService;


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

    @RequestMapping(value = {"/getsede"},method = RequestMethod.GET)
    public ResponseEntity<Object> getSede(){
        List<Sede> listaSede = sedeService.listAll();
        return new ResponseEntity<>(listaSede, HttpStatus.OK);

    }







    @RequestMapping(value= {"/home/news"}, method= RequestMethod.GET)
    public String news(){
        return "home/news";
    }



}


