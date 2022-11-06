
package com.primerSpringBoot.MiSpringBoot.service;

import com.primerSpringBoot.MiSpringBoot.model.Educacion;
import com.primerSpringBoot.MiSpringBoot.repository.EducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class EducacionService {
    
    @Autowired
    EducacionRepository educacionRepo;
    
    public List<Educacion> list(){
        return educacionRepo.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return educacionRepo.findById(id);
    }
    
    public Optional<Educacion> getByNombre(String nombre){
        return educacionRepo.findByNomEscuela(nombre);
    }
    
    public void save(Educacion edu){
        educacionRepo.save(edu);
    }
    
    public void delete(int id){
        educacionRepo.deleteById(id);
    }
    
    public boolean existsById(int id){
        return educacionRepo.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return educacionRepo.existsByNomEscuela(nombre);
    }
    
    
}
