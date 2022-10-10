
package com.primerSpringBoot.MiSpringBoot.service;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import com.primerSpringBoot.MiSpringBoot.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    public PersonaRepository persoRepo;
    
    @Override
    public List<Persona> verPersona() {
        return persoRepo.findAll();
    }

    @Override
    public void crearPersona(Persona per) {
        persoRepo.save(per);
    }

    @Override
    public void borrarPersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
//        sino encuentra a la persona devuelve null por eso el orElse
        return persoRepo.findById(id).orElse(null);
    }

    
}
