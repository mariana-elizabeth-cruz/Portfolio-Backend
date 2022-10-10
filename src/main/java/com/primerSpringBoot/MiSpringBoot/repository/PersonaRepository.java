
package com.primerSpringBoot.MiSpringBoot.repository;

import com.primerSpringBoot.MiSpringBoot.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//mapeamos como repositorio la interface extiende de JpaRepository
//(maneja repositorios JPA) en los parametros < > debe ir <clase a persistir,
//tipo de dato de su IDE> 
public interface PersonaRepository extends JpaRepository <Persona, Long> {
    
}
