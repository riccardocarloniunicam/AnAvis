package com.example.java.controller.Utente;


import com.example.java.model.Modulo;
import com.example.java.model.User;
import com.example.java.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ModuloController {


@Autowired
private User userr;
@Autowired
private ModuloService moduloService;

    @RequestMapping(value= {"/home/nuovo-modulo"}, method= RequestMethod.GET)
    public ModelAndView modulo() {
        ModelAndView model = new ModelAndView();
        Modulo modulo = new Modulo(); //vuota

        model.addObject("user",userr);
        model.addObject("modulo", modulo);
        model.setViewName("home/nuovo-modulo");
        return model;
    }




    @RequestMapping(value= {"/home/nuovo-modulo"}, method=RequestMethod.POST)
    public String nuovoModulo(@Valid Modulo modulo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws InterruptedException {

        ModelAndView model = new ModelAndView();

        if (moduloService.moduloEsiste(userr.getId())){
            bindingResult.rejectValue("nome", "error.modulo", "Hai già inviato il modulo. Attendi la revisione degli amministratori per inviarne uno nuovo");
        }else
        if (!moduloService.checkInput(modulo)){
            bindingResult.rejectValue("residenza", "error.modulo", "Per Favore completa tutti i campi");
        }
        if(bindingResult.hasErrors()) {
            return "redirect: /home/nuovo-modulo";
        }else {
            moduloService.saveModulo(modulo,userr.getId());
            model.addObject("modulo", new Modulo());
            redirectAttributes.addFlashAttribute("msg", "Stato dell'utente uppato con successo!");

        }
        return "redirect:/home/home";

    }
}
