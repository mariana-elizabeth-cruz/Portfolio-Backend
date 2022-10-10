
package com.primerSpringBoot.MiSpringBoot.service;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import java.util.List;

public interface IPersonaService {
    
    //metodo para traer todas la personas
    public List<Persona> verPersona();
//    metodo para dar alta a una persona
    public String crearPersona (Persona per);
//    metodo para borrar una persona
    public String borrarPersona(Long id);
//    metodo para encontrar una persona
    public Persona buscarPersona(Long id);
//    metodo para editar
    public Persona editarPersona(Long id, Persona nombre, Persona apellido);
}
