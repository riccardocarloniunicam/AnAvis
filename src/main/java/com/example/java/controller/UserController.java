package com.example.java.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.java.model.*;
import com.example.java.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UserController  {
    @Autowired
    private UserService userService;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private ModuloService moduloService;

    @Qualifier("user")
    @Autowired
    private User userr;

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

    @Autowired
    private NewsService newsService;
    private NewsService news;


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



    @RequestMapping(value = {"/getnews"},method = RequestMethod.GET)
    public ResponseEntity<Object> getNews(){
        List<News> listaNews = newsService.listall();
        return new ResponseEntity<>(listaNews, HttpStatus.OK);

    }


    @RequestMapping(value= {"/home/nuovo-modulo"}, method=RequestMethod.GET)
    public ModelAndView modulo() {
        ModelAndView model = new ModelAndView();
        Modulo modulo = new Modulo(); //vuota

        model.addObject("user",userr);
        model.addObject("modulo", modulo);
        model.setViewName("home/nuovo-modulo");
        return model;
    }


    public ModelAndView check(@Valid Modulo modulo, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();

        if (moduloService.moduloEsiste(userr.getId())){
            bindingResult.rejectValue("nome", "error.modulo", "Hai già inviato il modulo. Attendi la revisione degli amministratori per inviarne uno nuovo");
        }else
        if (!moduloService.checkInput(modulo)){
            bindingResult.rejectValue("residenza", "error.modulo", "Per Favore completa tutti i campi");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("home/nuovo-modulo");
        }else {
            moduloService.saveModulo(modulo,userr.getId());
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

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        fillInfo();
        ModelAndView model = new ModelAndView();
        model.addObject("user",userr);
        model.setViewName("home/home");
        return model;
    }


    //Prendo i dati della persona che chiama il metodo.
    @RequestMapping(value = "home/profile", method = RequestMethod.GET)
    public String profile(Model model, HttpServletRequest request) {
        return "redirect:/home/profile/" + userr.getNome();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/profile/{name}", method = RequestMethod.GET)
    public String seeProfileTrue(@PathVariable(value="name")String personEmail,Model model, HttpServletRequest request) {
        model.addAttribute("user",userr);
        return "home/profile";
    }




    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

