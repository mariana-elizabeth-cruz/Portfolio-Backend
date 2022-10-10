
package com.primerSpringBoot.MiSpringBoot.service;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import java.util.List;

public interface IPersonaService {
    
    //metodo para traer todas la personas
    public List<Persona> verPersona();
//    metodo para dar dxe alta a una persona
    public void crearPersona (Persona per);
//    metodo para borrar una persona
    public void borrarPersona(Long id);
//    metodo para encontrar una persona
    public Persona buscarPersona(Long id);
}
