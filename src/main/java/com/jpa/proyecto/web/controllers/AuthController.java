package com.jpa.proyecto.web.controllers;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jpa.proyecto.domain.dto.LoginRequest;
import com.jpa.proyecto.domain.dto.RegisterRequest;
import com.jpa.proyecto.domain.repositories.CuentaRepository;

import com.jpa.proyecto.persistence.entities.RolEnum;
import com.jpa.proyecto.security.jwt.JwtUtils;

import com.jpa.proyecto.persistence.entities.Cuenta;
import com.jpa.proyecto.domain.dto.AuthResponse;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")  // Uso de minúsculas para consistencia
public class AuthController {

    private final CuentaRepository repository;
    private final JwtUtils jwt;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(CuentaRepository repository, JwtUtils jwt, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails user = repository.findByName(loginRequest.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = jwt.getToken(user);
        return ResponseEntity.ok(AuthResponse.token(token).build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        Cuenta tercero = Cuenta.builder()
            .id(registerRequest.getId())
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .primerNombre(registerRequest.getPrimerNombre())
            .primerApellido(registerRequest.getPrimerApellido())
            .telefono(registerRequest.getTelefono())
            .rol(RolEnum.USER)
            .build();

        repository.save(tercero);

        String token = jwt.getToken(tercero);
        return ResponseEntity.ok(AuthResponse.token(token).build());
    }
}
