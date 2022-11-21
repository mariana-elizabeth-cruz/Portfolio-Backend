
package com.primerSpringBoot.MiSpringBoot.repository;

import com.primerSpringBoot.MiSpringBoot.model.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHyS extends JpaRepository<HyS, Integer> {
    Optional<HyS> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
