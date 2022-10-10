
package com.primerSpringBoot.MiSpringBoot.controller;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import com.primerSpringBoot.MiSpringBoot.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @Autowired
    private IPersonaService persoServ;
    
    @PostMapping ("/new/persona")
    //RequestBody solicitud al servidor
    public void agregarPersona(@RequestBody Persona pers){
        persoServ.crearPersona(pers);
    }
    
    @GetMapping ("/ver/personas")
    //Response para responder al cliente
    @ResponseBody
    public List<Persona> verPersona(){
        return persoServ.verPersona();
    }
    
    @DeleteMapping ("/delete/{id}")
    public void borrarPersona(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
    
    //Para editar una persona 
    @PutMapping ("/new/persona")
    public void crearNuevaPersona(@RequestBody Persona pers) {
        persoServ.crearPersona(pers);
    }
    
    
    
    
    
    
    
    
    /*
    @GetMapping("/hola")
    public String decirHola() {
        return "Hola Mundo";
    }*/
}
