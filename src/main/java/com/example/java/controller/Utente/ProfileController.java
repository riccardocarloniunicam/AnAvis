package com.example.java.controller.Utente;


import com.example.java.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {


    @Autowired
    private User userr;
    //Prendo i dati della persona che chiama il metodo.
    @RequestMapping(value = "home/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request) {
        return "redirect:/home/profile/" + userr.getNome();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/profile/{name}", method = RequestMethod.GET)
    public String seeProfileOfUser(@PathVariable(value="name")String personEmail, Model model, HttpServletRequest request) {
        model.addAttribute("user",userr);
        return "home/profile";
    }


}
