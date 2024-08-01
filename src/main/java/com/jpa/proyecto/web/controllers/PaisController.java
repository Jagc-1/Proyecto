package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.pais.PaisService;
import com.jpa.proyecto.persistence.entities.Pais;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisService servicio;

    @GetMapping
    public List<Pais> listarPaises() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> ver(@PathVariable Long id) {
        Optional<Pais> paisOpt = servicio.findById(id);
        if (paisOpt.isPresent()) {
            return ResponseEntity.ok(paisOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Pais pais, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(pais));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Pais pais, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Pais> paisOpt = servicio.update(id, pais);
        if (paisOpt.isPresent()) {
            return ResponseEntity.ok(paisOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pais> eliminar(@PathVariable Long id) {
        Optional<Pais> paisOpt = servicio.delete(id);
        if (paisOpt.isPresent()) {
            return ResponseEntity.ok(paisOpt.orElseThrow());
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