package com.example.java.controller.Utente;


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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class SignupController {
    @Autowired
    private UserService userService;


    @Autowired
    private ModuloService moduloService;



    @Autowired
    private NewsService newsService;
    private NewsService news;




    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }


    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) throws InterruptedException {

        User userExists = userService.findUserByEmail(user.getEmail());
        if (user.getNome() == null){
            bindingResult.rejectValue("nome", "error.user", "Completa tutti i campi");
        }
        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            return "user/signup";
        } else {
            userService.saveUser(user);

            model.addAttribute("user", new User());
            redirectAttributes.addFlashAttribute("signup","Account creato correttamente");
            return "redirect:/login";
        }


    }





    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

