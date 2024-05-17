package com.example.simuladorso.controllers;

import com.example.simuladorso.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sml")
public class simuController {


    @GetMapping
    public String mostrarSaludo(){
        GM.inicializaMemoria();
        GM.creaProceso(1,6);
        
        GM.creaProceso(2, 10);
        GM.creaProceso(3, 3);
        
        return GM.imprimeMemoria();
    }

}
