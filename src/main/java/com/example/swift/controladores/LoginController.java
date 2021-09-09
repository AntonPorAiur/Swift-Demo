package com.example.swift.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
public class LoginController {

    /**
     * Aquí podemo validar si el usuario ya se ha logeado
     * @return
     */
    @GetMapping("/login")
    public String login(@RequestParam(value="error", required = false) String error,
                        @RequestParam(value="logout", required = false) String logout,
                        Model model, Principal principal, RedirectAttributes flash) {

        //Por defecto se redirige a este controlador, aquí podemos personalizar mensajes
        if(principal != null){
            flash.addFlashAttribute("info","Ya has iniciado sesión anteriormente");
            return "redirect:/";
        }

        if(error != null){
            model.addAttribute("succes","Nombre o contraseña incorrecto, vuelve a intentar");
        }

        if(logout != null){
            model.addAttribute("info","Has cerrado sesión");
        }

        return "login";
    }
}
