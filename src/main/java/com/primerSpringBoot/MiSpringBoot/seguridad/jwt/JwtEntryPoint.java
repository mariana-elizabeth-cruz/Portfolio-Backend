
package com.primerSpringBoot.MiSpringBoot.seguridad.jwt;

import org.slf4j.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Getter //puede no estar
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    private final static Logger logger = (Logger) LoggerFactory.getLogger(JwtEntryPoint.class);
    
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException, ServletException {
        logger.error("Fallo en el metodo commence");
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
    }
    
}
