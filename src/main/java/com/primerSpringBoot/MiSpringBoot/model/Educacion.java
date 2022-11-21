
package com.primerSpringBoot.MiSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Educacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomEscuela;
    private String descripcion;
    private String ciudad;
    private String fechaInicio;
    private String fechaFin;
    
    @ManyToOne
    @JoinColumn(name = "per_id")
    @JsonManagedReference
    Persona persona;

    public Educacion() {
    }

    public Educacion(String nomEscuela, String descripcion, String ciudad, String fechaInicio, String fechaFin) {
        this.nomEscuela = nomEscuela;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
        
}
