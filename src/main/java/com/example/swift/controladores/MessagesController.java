package com.example.swift.controladores;


import com.example.swift.modelos.Operaciones;
import com.example.swift.servicio.IServicio;
import com.example.swift.to.MessageTo;
import com.example.swift.to.OperacionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MessagesController {

    @Autowired
    IServicio servicio;

    @PostMapping(value = "/createMessage/swift")
    public @ResponseBody MessageTo crearSwift(@RequestBody OperacionTo ops, Model model) {

        String message = "";
        MessageTo response = new MessageTo();
        ModelAndView mav = new ModelAndView("modal/previewMessage :: previewMessage");
        
        if(ops != null) {

            if(!ops.getOps().isEmpty()) {
                // System.out.println(ops);
                message = "Esta funcionando el modal con thymeleaf ";
                response = new MessageTo(message,(short) 1,message);
                // model.addAttribute("message", message);
                // flash.addFlashAttribute("success","Se recibieron los id");

                // mav.addObject("message", "Hola soy un string generado en el controller");
                // return mav;
            }
        }
    
        // return "modal/previewMessage :: previewMessage";
        return response;
    }

}
