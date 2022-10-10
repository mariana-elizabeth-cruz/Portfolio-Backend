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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    IPersonaService persoServ;

    @PostMapping("/new/persona")
    //RequestBody solicitud al servidor
    public String agregarPersona(@RequestBody Persona pers) {
        persoServ.crearPersona(pers);
        return "La persona fue creada exitosamente!";
    }

    @GetMapping("/ver/personas")
    //Response para responder al cliente
    @ResponseBody
    public List<Persona> verPersona() {
        return persoServ.verPersona();
    }

    @DeleteMapping("/delete/{id}")
    public String borrarPersona(@PathVariable Long id) {
        persoServ.borrarPersona(id);
        return "Se elimino una persona";
    }

    //Para editar una persona 
    @PutMapping("/editar/persona/{id}")
    public Persona crearNuevaPersona(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido) {
        Persona persona = persoServ.buscarPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persoServ.crearPersona(persona);

        return persona;
    }

    /*
    @GetMapping("/hola")
    public String decirHola() {
        return "Hola Mundo";
    }*/
}
