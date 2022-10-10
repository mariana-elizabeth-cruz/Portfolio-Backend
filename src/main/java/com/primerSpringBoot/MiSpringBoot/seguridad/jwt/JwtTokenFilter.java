
package com.primerSpringBoot.MiSpringBoot.seguridad.jwt;

import com.primerSpringBoot.MiSpringBoot.seguridad.service.UserDetailsServiceImp;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

//se ejecuta una vez por cada peticion, para login
public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(JwtTokenFilter.class);
    
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailsServiceImp userDetailsService;
    
    @Override
    //comprueva si el token es valido
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if(token != null && jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(nombreUsuario);
                //se crea una autenticacion
                UsernamePasswordAuthenticationToken auth = 
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("fallo el metodo doFilter");
        }
        filterChain.doFilter(req, resp);
    }
    
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Autorizacion");
        if(header != null && header.startsWith("Bearer."))
            return header.replace("Bearer","");
        return null;
    }
    
}
