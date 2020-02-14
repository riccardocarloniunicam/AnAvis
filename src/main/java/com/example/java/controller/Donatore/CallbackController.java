package com.example.java.controller.Donatore;


import com.example.java.model.Messaggi;
import com.example.java.model.News;
import com.example.java.model.User;
import com.example.java.service.MessaggiService;
import com.example.java.service.NewsService;
import com.example.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}


