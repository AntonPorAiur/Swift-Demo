package com.example.swift.controladores;

import com.example.swift.modelos.CuentasNostro;
import com.example.swift.modelos.Divisa;
import com.example.swift.modelos.Operaciones;
import com.example.swift.modelos.dao.OperacionesDao;
import com.example.swift.servicio.IServicio;
import com.example.swift.utilidades.PageRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("operacion")
public class OperacionesControl {

    @Autowired
    IServicio servicio;

    @RequestMapping(value = {"/listar","/"},method = RequestMethod.GET)
    public String listaroperaciones(
            @RequestParam(name = "page", defaultValue ="0") int page,
            @RequestParam(name = "keyword", defaultValue = "")String keyword,
//            @RequestParam(name = "contratoCli", defaultValue = "") String contratoCliente,
//            @RequestParam(name = "referencia", defaultValue = "")String referencia,
//            @RequestParam(name = "corresponsal", defaultValue = "")String corresponsal,
            Model model){

//        Pageable pageRequest = PageRequest.of(page,25);

//        Page<Operaciones> operaciones = servicio.findAll(pageRequest);
        Page<Operaciones> operaciones = servicio.findByCriteria(page,10,keyword);
//        PageRender<Operaciones> pageRender = new PageRender<>("/listar",operaciones);

        PageRender<Operaciones> pageRender = new PageRender<>("/listar",operaciones,keyword);

        model.addAttribute("titulo","Listado de operaciones");
//        model.addAttribute("operaciones",servicio.findAll());
        model.addAttribute("operaciones",operaciones);
        model.addAttribute("page",pageRender);

        return "listarOperaciones";
    }

    @RequestMapping(value = "/formOperacion")
    public String crearOperacion(Map<String, Object> model) {

    //    Operaciones operacion = new Operaciones();
        //Para evitar error en render de formOperacion para crear una nueva operacion
        // List<Divisa> divisas = servicio.findAllDivisas();
        // List<CuentasNostro> corresponsales = servicio.findAllCorresponsales();
//
//        operacion.setMoneda(divisas.get(0));
//        operacion.setCorresponsal(corresponsales.get(0));
//
    //    model.put("operacion",operacion);
        model.put("titulo","Alta operación");
        // model.put("divisas",divisas);
        // model.put("corresponsales",corresponsales);

        return "formOperacion";
    }

    @RequestMapping(value = "/formOperacion/{id}")
    public String editarOperacion(@PathVariable (value = "id") Long id, Map<String, Object> model,RedirectAttributes flash){

        Operaciones operacion = null;
        List<Divisa> divisas = new ArrayList<>(Collections.emptyList());
        List<CuentasNostro> corresponsales = new ArrayList<>(Collections.emptyList());
        // Long divisaId = new Long(0);

        if(id > 0){
            operacion = servicio.findOne(id);

            divisas = servicio.findAllDivisas();
            corresponsales = servicio.findAllCorresponsales();

            if(operacion == null) {
                flash.addFlashAttribute("error","La operacion no existe en registro");
                return "redirect:/listar";
            }
        }else {
            flash.addAttribute("error","El id de operacion no puede ser cero");
            return "redirect:/listar";
        }

        model.put("operacion",operacion);
        model.put("divisas",divisas);
        model.put("corresponsales",corresponsales);
//        model.put("divisa.id",divisaId);
        model.put("titulo","Editar operación");
        return "formOperacion";
    }


    @RequestMapping(value = "/formOperacion", method = RequestMethod.POST)
    public String guardar(Operaciones operacion, Model model, RedirectAttributes flash, SessionStatus status){
        //La anotación valid es necesaria para que se hagan las validaciones en objeto Cliente
        //BindingResult debe ir junto con objeto Cliente que es el que se devulve a la vista
//        if(result.hasErrors()){
//            model.addAttribute("titulo","Formulario cliente");
//            return "formOperaciones";
//        }

        String mensajeFlash = (operacion.getId()!=null)? "Cliente editado con éxito" : "Cliente creado con éxito";

        servicio.save(operacion);
        status.setComplete();    //En este momento de destruye objeto operacion despues de persistir
        flash.addFlashAttribute("success", mensajeFlash);
        return "redirect:/listar";
    }


    @RequestMapping("/eliminar/{id}")
    public String eliminarOperacion(@PathVariable (value = "id") Long id, RedirectAttributes flash){

        if(id > 0){
            servicio.eliminarOperacion(id);
            flash.addFlashAttribute("success","Operación eliminada con éxito");
        }

        return "redirect:/listar";
    }

    @PostMapping(value = "/nuevaOperacion")
    public String guardarRest(Operaciones operacion) {

        servicio.save(operacion);

        return "redirect:/listar";
    }

    @RequestMapping(value = "/formOperacion/modal",method = RequestMethod.GET) 
    public String getFormModal(Model model) {

        // model.addAttribute("corresponsales", servicio.findAllCorresponsales());
        Operaciones operacion = new Operaciones();
        model.addAttribute("operacion",operacion);

        return "modal/modalForm :: addNewModal";

    }

}
