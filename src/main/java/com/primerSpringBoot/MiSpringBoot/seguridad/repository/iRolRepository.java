
package com.primerSpringBoot.MiSpringBoot.seguridad.repository;

import com.primerSpringBoot.MiSpringBoot.seguridad.entity.Rol;
import com.primerSpringBoot.MiSpringBoot.seguridad.enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
    
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
