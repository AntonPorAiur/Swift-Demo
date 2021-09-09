package com.example.swift.controladores;

import com.example.swift.modelos.CuentasNostro;
import com.example.swift.modelos.Divisa;
import com.example.swift.servicio.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class Catalogos {

    @Autowired
    IServicio servicio;

    @GetMapping(value = "/catalogos/corresponsales")
    public @ResponseBody List<CuentasNostro> listarCorresponsales(CuentasNostro nostro) {
        return servicio.findAllCorresponsales();
    }

    @GetMapping(value = "/catalogos/divisas")
    public @ResponseBody List<Divisa> listarDivisas(){ return servicio.findAllDivisas(); }

}