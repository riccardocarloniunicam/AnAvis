package com.example.java.controller.Sede;


import com.example.java.model.Messaggi;
import com.example.java.model.Prenotazioni;
import com.example.java.model.User;
import com.example.java.model.UtenteSedi;
import com.example.java.service.MessaggiService;
import com.example.java.service.PrenotazioniService;
import com.example.java.service.SedeService;
import com.example.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PrenotazioniSedeController {


    @Autowired
    private PrenotazioniService prenotazioniService;

    @Autowired
    private UserService userService;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private MessaggiService messaggiService;

    @RequestMapping(value = "/sede/prenotazioni",method = RequestMethod.GET)
    public String getSede(Model model, HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "redirect:/sede/prenotazioni/" + auth.getName();
    }

    @RequestMapping(value = "/sede/prenotazioni/{email}", method = RequestMethod.GET)
    public String getPrenotazioniInSede(@PathVariable(value="email")String email, Model model, HttpServletRequest request) {
      //  List<User> user = userService.findUserOfPrenotazione(email);
        List<Prenotazioni> prenotazione = prenotazioniService.appuntamentiInSede(email);
        model.addAttribute("prenotazione",prenotazione);
        return "/sede/prenotazioni";
    }


    @RequestMapping(value = "/getuserprenotazioni",method = RequestMethod.POST)
    public String getUserPrenotazioni(@Valid Prenotazioni prenotazioni, Model model, HttpServletRequest request){
        return "redirect:/sede/info_user_prenotazione/" + prenotazioni.getEmail();
    }


    @RequestMapping(value = "/sede/info_user_prenotazione/{email}",method = RequestMethod.GET)
    public String infoUserPrenotazione(@PathVariable(value = "email")String email,Model model,HttpServletRequest request){
        User user = userService.findUserByEmail(email);
        model.addAttribute("user",user);
        return "/sede/info_user_prenotazione";

    }


    @RequestMapping(value = "/cancellaprenotazione",method = RequestMethod.POST)
    public String cancellaPrenotazione(@Valid Prenotazioni prenotazioni,Model model,HttpServletRequest request){
        return "redirect:/sede/cancellaprenotazione/"+prenotazioni.getSede_id()+"/"+prenotazioni.getId()+"/"+prenotazioni.getEmail();
    }


    @RequestMapping(value = "/sede/cancellaprenotazione/{sede_id}/{prenotazioni_id}/{email}",method = RequestMethod.GET)
    public String cancellaPrenotazionee(@PathVariable(value = "sede_id")Integer sede_id,
                                        @PathVariable(value = "prenotazioni_id")Integer prenotazione_id,
                                        @PathVariable(value = "email")String email,
                                        Model model,HttpServletRequest request){
        model.addAttribute("sede",sedeService.getSede(sede_id));
        model.addAttribute("prenotazione",prenotazioniService.getPrenotazionebyID(prenotazione_id));
        model.addAttribute("user",userService.findUserByEmail(email));
        return "sede/cancellaprenotazione";
    }


    @RequestMapping(value = "/eliminadefinitivamente",method = RequestMethod.POST)
    public String eliminaDefinitivamente(@Valid Messaggi messaggi,User user ,RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        messaggiService.saveMessage(messaggi,auth.getName(),user.getId());//salva messaggi
        prenotazioniService.delete(user.getId());
        redirectAttributes.addFlashAttribute("eliminato","Prenotazione eliminata correttamente");
        return "redirect:/sede/home";
    }

    @RequestMapping(value = "/eseguita",method = RequestMethod.POST)
    public String eseguita(@Valid Prenotazioni prenotazioni,RedirectAttributes redirectAttributes){
        prenotazioniService.updateState(prenotazioni.getId());
        redirectAttributes.addFlashAttribute("eseguita","Prenotazione eseguita con successo");
        return "redirect:sede/home";
    }


}
