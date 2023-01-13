
package com.primerSpringBoot.MiSpringBoot.service;

import com.primerSpringBoot.MiSpringBoot.model.Experiencia;
import com.primerSpringBoot.MiSpringBoot.repository.ExperienciaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ExperienciaService {
    
    @Autowired
    ExperienciaRepository experienciaRepo;
    
    public List<Experiencia> list(){
        return experienciaRepo.findAll();
    }
    
    public Optional<Experiencia> getOne(int id){
        return experienciaRepo.findById(id);
    }
    
    public Optional<Experiencia> getByNombreExp(String nombreExp){
        if(nombreExp.isEmpty()) {
            return null;
        } else {
            return experienciaRepo.findByNombreExp(nombreExp);
        }
    }
    
    public void save(Experiencia exp) {
        experienciaRepo.save(exp);
    }
    
    public void delete(int id) {
        experienciaRepo.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return experienciaRepo.existsById(id);
    }
    
    public boolean existsByNombreExp(String nombreExp) {
        return experienciaRepo.existsByNombreExp(nombreExp);
    }
    
    
}
