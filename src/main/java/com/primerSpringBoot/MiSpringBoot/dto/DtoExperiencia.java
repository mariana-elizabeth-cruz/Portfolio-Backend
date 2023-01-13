
package com.primerSpringBoot.MiSpringBoot.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoExperiencia {
    
    @NotBlank
    private String nombreExp;
    @NotBlank
    private String descripcion;
    @NotBlank
    private String fechaInicio;
    @NotBlank
    private String fechaFin;

    public DtoExperiencia() {
    }

    public DtoExperiencia(String nombreExp, String descripcion, String fechaInicio, String fechaFin) {
        this.nombreExp = nombreExp;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
   
}
