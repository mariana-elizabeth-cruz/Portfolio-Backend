
package com.primerSpringBoot.MiSpringBoot.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DtoHyS {

    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;

    public DtoHyS() {
    }

    public DtoHyS(String nombre, int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }
    
    
}
