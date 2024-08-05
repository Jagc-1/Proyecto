package com.jpa.proyecto.domain.services.userdetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.CuentaRepository;
import com.jpa.proyecto.persistence.entities.Cuenta;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private CuentaRepository repository;

    public UserDetails findByUserName(String nombre) throws UsernameNotFoundException{
        List<Cuenta> cuentas = repository.findByName(nombre);

        if(cuentas.isEmpty()){
            throw(new UsernameNotFoundException(("El usuario con " +  nombre + " no existe!!!")));
        }

        Cuenta cuentaFound = cuentas.get(0);

        Collection<? extends GrantedAuthority> authorities = cuentaFound.getRol()
        .stream()
        .map(rol -> new SimpleGrantedAuthority("Rol: ".concat(rol.getNombre().name())))
        .collect(Collectors.toSet());

        return new User(
            cuentaFound.getName(),
            cuentaFound.getContrasena(),
            authorities
        );
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        List<Cuenta> cuentas = repository.findByName(nombre);

        if(cuentas.isEmpty()){
            throw(new UsernameNotFoundException(("El usuario con " +  nombre + " no existe!!!")));
        }

        Cuenta cuentaFound = cuentas.get(0);

        Collection<? extends GrantedAuthority> authorities = cuentaFound.getRol()
        .stream()
        .map(rol -> new SimpleGrantedAuthority("Rol: ".concat(rol.getNombre().name())))
        .collect(Collectors.toSet());

        return new User(
            cuentaFound.getName(),
            cuentaFound.getContrasena(),
            authorities
        );
    }
}

