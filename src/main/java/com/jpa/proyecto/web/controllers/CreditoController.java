package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.jpa.proyecto.domain.services.credito.CreditoService;
import com.jpa.proyecto.persistence.entities.Credito;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    @Autowired
    private CreditoService servicio;

    @GetMapping
    public List<Credito> listarCreditos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Credito> ver(@PathVariable Long id) {
        Optional<Credito> creditoOpt = servicio.findById(id);
        if (creditoOpt.isPresent()) {
            return ResponseEntity.ok(creditoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Credito credito, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(credito));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Credito credito, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Credito> creditoOpt = servicio.update(id, credito);
        if (creditoOpt.isPresent()) {
            return ResponseEntity.ok(creditoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Credito> eliminar(@PathVariable Long id) {
        Optional<Credito> creditoOpt = servicio.delete(id);
        if (creditoOpt.isPresent()) {
            return ResponseEntity.ok(creditoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validar(BindingResult resultado) {
        Map<String, String> errores = new HashMap<>();
        resultado.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}