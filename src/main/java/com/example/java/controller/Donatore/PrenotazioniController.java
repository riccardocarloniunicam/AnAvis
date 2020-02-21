package com.example.java.controller.Donatore;

import com.example.java.model.Orario;
import com.example.java.model.Prenotazioni;
import com.example.java.model.Sede;
import com.example.java.model.User;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PrenotazioniController {

    @Autowired
    private PrenotazioniService prenotazioniService;
    @Autowired
    private SedeService sedeService;
    @Autowired
    private User userr;



    @RequestMapping(value = "home/prenotazioni", method = RequestMethod.GET)
    public String prenotazioni(Model model, HttpServletRequest request) {
        Prenotazioni prenotazioni = prenotazioniService.getPrenotazioni(userr.getId());
        if (prenotazioni==null){
            model.addAttribute("prenotazione","null");
        }else {
            model.addAttribute("prenotazione",prenotazioniService.getPrenotazionebyID(prenotazioni.getId()));
            model.addAttribute("sede",sedeService.getSede(prenotazioni.getSede_id()));

        }
        return "home/prenotazioni";

    }


    @RequestMapping(value = "home/cancella-prenotazione", method = RequestMethod.GET)
    public String cancellaPrenotazione(Model model, HttpServletRequest request) {
        return "redirect:/home/cancella-prenotazione/" + userr.getId();
    }

    //get the person and all his informations;
    @RequestMapping(value = "/home/cancella-prenotazione/{id}", method = RequestMethod.GET)
    public String CancellaPrenotazioneid(@PathVariable(value="id")Integer  id, Model model, HttpServletRequest request) {
        prenotazioniService.delete(id);
        model.addAttribute("prenotazione","null");
        model.addAttribute("msg", "Prenotazione Cancellata con Successo!");
        return "home/prenotazioni";
    }


    @RequestMapping(value= {"/home/nuova-analisi"}, method=RequestMethod.GET)
    public String prenota(RedirectAttributes redirectAttributes) {
        if (prenotazioniService.prenotazioneEffettuata(userr.getId())) {
            redirectAttributes.addFlashAttribute("prenotazionemax", "Hai effettuato il numero massimo di prenotazioni!");
            return "redirect:/home/home";
        }else{
            return "home/nuova-analisi";
        }


    }



    @RequestMapping(value = {"returnhome"},method = RequestMethod.GET)
    public String ritorno(Model model,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("prenotazione","Prenotazione Eseguita con successo");
        return "redirect:/home/home/";

    }
    //PRENOTAZIONI POST
    @RequestMapping(value ={"/home/nuova-analisi"} , method = RequestMethod.POST)
    public String confermaPrenotazione(@Valid Prenotazioni prenotazioni, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws InterruptedException {
        if (prenotazioniService.prenotazioneEffettuata(userr.getId())){
            redirectAttributes.addFlashAttribute("prenotazionemax","Hai effettuato il numero massimo di prenotazioni!");
            return "redirect:/home/home";
        }else {
            prenotazioniService.savePrenotazione(prenotazioni,userr.getId(),userr.getNome(),userr.getCognome(),userr.getEmail());
            redirectAttributes.addFlashAttribute("prenotazione","Prenotazione effettuata con successo");
            return "redirect:/home/home";

        }

    }

}
