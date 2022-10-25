package com.primerSpringBoot.MiSpringBoot.seguridad.controller;

import com.primerSpringBoot.MiSpringBoot.seguridad.dto.JwtDto;
import com.primerSpringBoot.MiSpringBoot.seguridad.dto.LoginUsuario;
import com.primerSpringBoot.MiSpringBoot.seguridad.dto.NuevoUsuario;
import com.primerSpringBoot.MiSpringBoot.seguridad.entity.Rol;
import com.primerSpringBoot.MiSpringBoot.seguridad.entity.Usuario;
import com.primerSpringBoot.MiSpringBoot.seguridad.enums.RolNombre;
import com.primerSpringBoot.MiSpringBoot.seguridad.jwt.JwtProvider;
import com.primerSpringBoot.MiSpringBoot.seguridad.service.RolService;
import com.primerSpringBoot.MiSpringBoot.seguridad.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AutenticacionController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioServicio;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campo mal puesto o email invalido."), HttpStatus.BAD_REQUEST);
        
        if (usuarioServicio.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) 
            return new ResponseEntity(new Mensaje("Ese nombre ya existe."), HttpStatus.BAD_REQUEST);
        
        if (usuarioServicio.existsByEmail(nuevoUsuario.getEmail())) 
            return new ResponseEntity(new Mensaje("Ese email ya existe."), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if (nuevoUsuario.getRoles().contains("admin")) 
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        
        usuario.setRoles(roles);
        usuarioServicio.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario guardado."), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> loggin(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            return new ResponseEntity(new Mensaje("Campos mal puesto."), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }
}
