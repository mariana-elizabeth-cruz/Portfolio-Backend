
package com.primerSpringBoot.MiSpringBoot.seguridad.entity;

import com.primerSpringBoot.MiSpringBoot.seguridad.enums.RolNombre;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Enumerated (EnumType.STRING)
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(int id, RolNombre rolNombre) {
        this.id = id;
        this.rolNombre = rolNombre;
    }

   
    
}
