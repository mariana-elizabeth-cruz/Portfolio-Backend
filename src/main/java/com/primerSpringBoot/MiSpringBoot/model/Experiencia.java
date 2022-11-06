
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
public class Experiencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreExp;
    private String duracion;
    
    @ManyToOne
    @JoinColumn(name = "per_id")
    @JsonManagedReference
    Persona persona;

    public Experiencia() {
    }

    public Experiencia(String nombreExp, String duracion) {
        this.nombreExp = nombreExp;
        this.duracion = duracion;
    }
    
    
    
}
