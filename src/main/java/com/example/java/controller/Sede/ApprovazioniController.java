package com.example.java.controller.Sede;


import com.example.java.model.Modulo;
import com.example.java.model.User;
import com.example.java.service.ModuloService;
import com.example.java.service.UserService;
import com.example.java.service.UtenteSediService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ApprovazioniController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModuloService moduloService;

    @Autowired
    private UtenteSediService utenteSediService;

    @RequestMapping(value = "sede/approvazioni")
    public ModelAndView approvazioni(){
        ModelAndView model = new ModelAndView();
        List<User> user = userService.findToApprove();
        model.addObject("user",user);
        model.setViewName("sede/approvazione");
        return model;
    }

    @RequestMapping(value = "/getuser",method = RequestMethod.POST)
    public String getUser(@Valid User user,Model model,HttpServletRequest request){
        return "redirect:/sede/info_user/" + user.getId();
    }


    //get the person and all his informations;
    @RequestMapping(value = "/sede/info_user/{id}", method = RequestMethod.GET)
    public String seeProfileTrue(@PathVariable(value="id")Integer id,Model model, HttpServletRequest request) {
      User info = userService.findByIdForApprovazione(id);
      Modulo modulo = moduloService.findDaID(id);
      model.addAttribute("info",info);
      model.addAttribute("modulo",modulo);
        return "sede/info_user";
    }



    @RequestMapping(value = "/approvazione",method = RequestMethod.POST)
    public String rendiDonatore(@Valid User user, Model model, RedirectAttributes redirectAttributes){
        userService.UpgradeRole(user.getId());
        userService.UpgradeParamDonatore(user.getId());
        redirectAttributes.addFlashAttribute("msg", "Stato dell'utente uppato con successo!");
        return "redirect:/sede/approvazioni/";

    }




    //Cancella valori
    @RequestMapping(value = "/disapprovazione",method = RequestMethod.POST)
    public String respingiModulo(@Valid User user, Model model, RedirectAttributes redirectAttributes){
        moduloService.deleteById(user.getId());
        userService.downGradeParamModulo(user.getId()); // NON FUNZIONA PORCODIDO
        redirectAttributes.addFlashAttribute("delete", "Modulo Respinto");
        return "redirect:/sede/approvazioni/";

    }









}
