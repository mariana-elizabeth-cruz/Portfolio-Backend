
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

    public DtoEducacion() {
    }

    public DtoEducacion(String nomEscuela, String descripcion, String ciudad) {
        this.nomEscuela = nomEscuela;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
    }
    
    
}
