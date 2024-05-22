package com.example.simuladorso.controllers;

import com.example.simuladorso.domain.*;

import java.util.LinkedList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/sml")
public class simuController {


    @GetMapping("/prender")
    public String mostrarSaludo(){
        
        GM.inicializaMemoria();
        GM.creaProceso(1,6);
        GM.creaProceso(2, 5);
        GM.creaProceso(3, 2);
        
        
        
        return GM.imprimeMemoria();
    }
    @GetMapping("/memoria")
    public String memoria(){
        return GM.imprimeCadena();
    }

    @GetMapping("/lista")
    public ResponseEntity<?> mandarProcesos(){
        
        return ResponseEntity.ok(GM.listaControl);
    }


    @PostMapping("/crear")
    public ResponseEntity<Void> crearProceso(@RequestBody ProcesoDTO procesoDTO) {
        GM.creaProceso(procesoDTO.getPid(), procesoDTO.getTam());
        System.out.println("Proceso creado con PID: " + procesoDTO.getPid() + " y Tamaño: " + procesoDTO.getTam());
        return ResponseEntity.ok().build();
    }


    // Endpoint para eliminar un proceso por PID
    @DeleteMapping("/eliminar/{pid}")
    public ResponseEntity<String> eliminarProceso(@PathVariable int pid) {
        if (GM.destruyeProceso(pid)) {
            return ResponseEntity.ok("Proceso con PID " + pid + " eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    

}
