
package com.primerSpringBoot.MiSpringBoot.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoEducacion {
    
    @NotBlank
    private String nomEscuela;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String ciudad;
    @NotBlank
    private String fechaInicio;
    @NotBlank
    private String fechaFin;

    public DtoEducacion() {
    }

    public DtoEducacion(String nomEscuela, String descripcion, String ciudad, String fechaInicio, String fechaFin) {
        this.nomEscuela = nomEscuela;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    
}
