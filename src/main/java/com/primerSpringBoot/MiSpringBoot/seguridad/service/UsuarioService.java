
package com.primerSpringBoot.MiSpringBoot.seguridad.service;

import com.primerSpringBoot.MiSpringBoot.seguridad.entity.Usuario;
import com.primerSpringBoot.MiSpringBoot.seguridad.repository.iUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    
    @Autowired
    iUsuarioRepository userRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return userRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return userRepository.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String emaill){
        return userRepository.existsByEmail(emaill);
    }
    //metodo para guardar usuario nuevo
    public void save(Usuario usuario){
        userRepository.save(usuario);
    }
}
