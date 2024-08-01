package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.oficina.OficinaService;
import com.jpa.proyecto.persistence.entities.Oficina;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {

    @Autowired
    private OficinaService servicio;

    @GetMapping
    public List<Oficina> listarOficinas() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oficina> ver(@PathVariable Long id) {
        Optional<Oficina> oficinaOpt = servicio.findById(id);
        if (oficinaOpt.isPresent()) {
            return ResponseEntity.ok(oficinaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Oficina oficina, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(oficina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Oficina oficina, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Oficina> oficinaOpt = servicio.update(id, oficina);
        if (oficinaOpt.isPresent()) {
            return ResponseEntity.ok(oficinaOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Oficina> eliminar(@PathVariable Long id) {
        Optional<Oficina> oficinaOpt = servicio.delete(id);
        if (oficinaOpt.isPresent()) {
            return ResponseEntity.ok(oficinaOpt.orElseThrow());
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