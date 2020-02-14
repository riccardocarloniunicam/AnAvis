package com.example.java.controller.Utente;

import com.example.java.model.User;
import com.example.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Qualifier("user")
    @Autowired
    private User userr;

    @Autowired
    private UserService userService;


    public  User getUserr(){
        return userr;
    }
    //Riempte l'oggetto user.
    public User fillInfo(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = userService.findUserByEmail(auth.getName());
        userr.setId(u.getId());
        userr.setNome(u.getNome());
        userr.setCognome(u.getCognome());
        userr.setEmail(u.getEmail());
        userr.setCf(u.getCf());
        userr.setIndirizzo(u.getIndirizzo());
        userr.setCitta(u.getCitta());
        userr.setProvincia(u.getProvincia());
        userr.setCap(u.getCap());
        userr.setTelefono(u.getTelefono());
        userr.setTipo_documento(u.getTipo_documento());
        userr.setNumerodoc(u.getNumerodoc());
        userr.setGrupposanguinio(u.getGrupposanguinio());
        userr.setDonatore(u.getDonatore());
        userr.setEmail(u.getEmail());
        userr.setModulo(u.getModulo());
        return userr;
    }

    @RequestMapping(value= {"/home/home"}, method= RequestMethod.GET)
    public ModelAndView home() {
        fillInfo();
        ModelAndView model = new ModelAndView();
        model.addObject("user",userr);
        model.setViewName("home/home");
        return model;
    }
}
