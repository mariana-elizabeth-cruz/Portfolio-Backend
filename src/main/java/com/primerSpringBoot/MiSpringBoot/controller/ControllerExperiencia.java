
package com.primerSpringBoot.MiSpringBoot.controller;

import com.primerSpringBoot.MiSpringBoot.dto.DtoExperiencia;
import com.primerSpringBoot.MiSpringBoot.model.Experiencia;
import com.primerSpringBoot.MiSpringBoot.seguridad.controller.Mensaje;
import com.primerSpringBoot.MiSpringBoot.service.ExperienciaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/experiencia")
public class ControllerExperiencia {
    
    @Autowired
    ExperienciaService expeService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = expeService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if(!expeService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id de experiencia"), HttpStatus.BAD_REQUEST);
        }
        Experiencia exp = expeService.getOne(id).get();
        return new ResponseEntity(exp, HttpStatus.OK);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if(!expeService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
        expeService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody DtoExperiencia dtoexp) {
        if(StringUtils.isBlank(dtoexp.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre de la experiencia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(expeService.existsByNombreExp(dtoexp.getNombreExp())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Experiencia exp = new Experiencia(dtoexp.getNombreExp(), dtoexp.getDescripcion(), dtoexp.getFechaInicio(), dtoexp.getFechaFin());
        expeService.save(exp);
        
        return new ResponseEntity(new Mensaje("Experiencia cargada correctamente"), HttpStatus.OK);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoExperiencia dtoexpe) {
//        validar id
        if(!expeService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el id"), HttpStatus.NOT_FOUND);
        }
//        validar nombre
        if (expeService.existsByNombreExp(dtoexpe.getNombreExp()) && expeService.getByNombreExp(dtoexpe.getNombreExp()).get().getId() != id ) {
            return new ResponseEntity(new Mensaje("El nombre la experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
//        verificar que los campos no esten vacios
        if (StringUtils.isBlank(dtoexpe.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre de la experiencia es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoexpe.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Experiencia exp = expeService.getOne(id).get();
        exp.setNombreExp(dtoexpe.getNombreExp());
        exp.setDescripcion(dtoexpe.getDescripcion());
        exp.setFechaInicio(dtoexpe.getFechaInicio());
        exp.setFechaFin(dtoexpe.getFechaFin());
        
        expeService.save(exp);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
        
    } 
    
}
