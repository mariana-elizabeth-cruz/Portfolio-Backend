
package com.primerSpringBoot.MiSpringBoot.repository;

import com.primerSpringBoot.MiSpringBoot.model.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionRepository extends JpaRepository<Educacion, Integer>{
    Optional<Educacion> findByNomEscuela(String nomEscuela);
    public boolean existsByNomEscuela(String nomEscuela);
    
}
