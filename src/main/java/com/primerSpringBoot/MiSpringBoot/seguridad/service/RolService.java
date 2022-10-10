
package com.primerSpringBoot.MiSpringBoot.seguridad.service;

import com.primerSpringBoot.MiSpringBoot.seguridad.entity.Rol;
import com.primerSpringBoot.MiSpringBoot.seguridad.enums.RolNombre;
import com.primerSpringBoot.MiSpringBoot.seguridad.repository.iRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    
    @Autowired
    iRolRepository rolRepo;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepo.findByRolNombre(rolNombre);
    }
    
    //metodo para guardar cambios
    public void save(Rol rol){
        rolRepo.save(rol);
    }
}
