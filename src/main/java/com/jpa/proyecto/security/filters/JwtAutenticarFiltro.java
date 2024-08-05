package com.jpa.proyecto.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa.proyecto.persistence.entities.Cuenta;
import com.jpa.proyecto.security.jwt.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAutenticarFiltro extends UsernamePasswordAuthenticationFilter{

    private JwtUtils jwtUtils;

    public JwtAutenticarFiltro(  JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("sssssssssssssssssssssssssssssssssssssssss");

        Cuenta cuenta = null;
        String nombre = "";
        String contrasena = "";


        try {
            cuenta = new ObjectMapper().readValue(request.getInputStream(), Cuenta.class);

            // obtiene email y password de la entidad
            nombre = cuenta.getName();
            contrasena = cuenta.getContrasena();
            System.out.println("En proceso de autenticacion con nombre: " + nombre);


        } catch (StreamReadException e) {
            System.out.println("Error en la lectura: " +  e.getCause());
        } catch (DatabindException e) {
            System.out.println("Error en la databind: " +  e.getCause());
        } catch (IOException e) {
            System.out.println("Error en IOException: " +  e.getCause());
        }

        // autenticacion
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(nombre, contrasena);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

            User user =  (User) authResult.getPrincipal(); // se obtiene el objeto de la autenticacion


            String token = jwtUtils.generateAccesToken(user.getUsername());

            response.addHeader("Authorization", token);

            Map<String, Object> httpResponse = new HashMap<>();

            httpResponse.put("token", token);
            httpResponse.put("Message", "Autenticacion correcta");
            httpResponse.put("nombre: ", user.getUsername());

            // Convierte el mapa a JSON y escribe en el cuerpo de la respuesta

            response.getWriter().write( new ObjectMapper().writeValueAsString(httpResponse));
            response.setContentType("application/json");
            response.setStatus(200);
            response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
