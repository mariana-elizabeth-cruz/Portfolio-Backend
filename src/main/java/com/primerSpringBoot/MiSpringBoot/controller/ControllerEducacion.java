package com.primerSpringBoot.MiSpringBoot.controller;

import org.apache.commons.lang3.StringUtils;
import com.primerSpringBoot.MiSpringBoot.dto.DtoEducacion;
import com.primerSpringBoot.MiSpringBoot.model.Educacion;
import com.primerSpringBoot.MiSpringBoot.seguridad.controller.Mensaje;
import com.primerSpringBoot.MiSpringBoot.service.EducacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/educacion")
public class ControllerEducacion {
    
    @Autowired
    EducacionService eduservice;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = eduservice.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id) {
       if(!eduservice.existsById(id)) {
           return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.BAD_REQUEST);
       }
       Educacion edu = eduservice.getOne(id).get();
       return new ResponseEntity(edu, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!eduservice.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        eduservice.delete(id);
        return new ResponseEntity(new Mensaje("Educación eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoEducacion dtoedu) {
        if(StringUtils.isBlank(dtoedu.getNomEscuela())) {
            return new ResponseEntity(new Mensaje("El nombre de la Institución es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(eduservice.existsByNomEscuela(dtoedu.getNomEscuela())){
            return new ResponseEntity(new Mensaje("El nombre de la Institución ya existe"), HttpStatus.BAD_REQUEST);
        }
        Educacion edu = new Educacion(dtoedu.getNomEscuela(), dtoedu.getDescripcion(), dtoedu.getCiudad(), dtoedu.getFechaInicio(), dtoedu.getFechaFin());
        eduservice.save(edu);
        
        return new ResponseEntity(new Mensaje("Institución agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoEducacion dtoedu) {
//        valida el id
        if(!eduservice.existsById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.NOT_FOUND);
        }
//        valida el nombre
        if(eduservice.existsByNomEscuela(dtoedu.getNomEscuela()) && eduservice.getByNomEscuela(dtoedu.getNomEscuela())
                .get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa Institución ya existe"), HttpStatus.BAD_REQUEST);
        }
        
//        verifca que los campos no estes vacios
        if(StringUtils.isBlank(dtoedu.getNomEscuela())) {
            return new ResponseEntity(new Mensaje("El nombre de la Institución es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoedu.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoedu.getCiudad())){
            return new ResponseEntity(new Mensaje("Debe ingresar el nombre de la ciudad"), HttpStatus.BAD_REQUEST);
        } 
        
        Educacion edu = eduservice.getOne(id).get();
        edu.setNomEscuela(dtoedu.getNomEscuela());
        edu.setDescripcion(dtoedu.getDescripcion());
        edu.setCiudad(dtoedu.getCiudad());
        edu.setFechaInicio(dtoedu.getFechaInicio());
        edu.setFechaFin(dtoedu.getFechaFin());
        
        eduservice.save(edu);
        return new ResponseEntity(new Mensaje("Educación actualizada"), HttpStatus.OK);
    }
    
}
