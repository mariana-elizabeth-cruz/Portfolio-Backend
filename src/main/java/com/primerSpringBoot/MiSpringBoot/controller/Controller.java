package com.primerSpringBoot.MiSpringBoot.controller;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import com.primerSpringBoot.MiSpringBoot.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    IPersonaService persoServ;

//    @PreAuthorize("hasRole('ADMIN')")
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

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String borrarPersona(@PathVariable Long id) {
        persoServ.borrarPersona(id);
        return "Se elimino una persona";
    }

    //Para editar una persona 
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/persona/{id}")
    public Persona editarPersona(@PathVariable Long id,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido, 
            @RequestParam("imagen") String nuevoImg) {
        Persona persona = persoServ.buscarPersona(id);

        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        persoServ.crearPersona(persona);

        return persona;
    }
    
    @GetMapping("/persona/traer/perfil")
    public Persona findPersona(){
        return persoServ.buscarPersona((long)1);
    }


}
