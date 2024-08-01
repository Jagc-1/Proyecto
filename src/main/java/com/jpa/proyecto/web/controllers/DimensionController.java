package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.dimensiones.DimensionService;
import com.jpa.proyecto.persistence.entities.Dimension;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/dimensiones")
public class DimensionController {

    @Autowired
    private DimensionService servicio;

    @GetMapping
    public List<Dimension> listarDimensiones() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dimension> ver(@PathVariable Long id) {
        Optional<Dimension> dimensionesOpt = servicio.findById(id);
        if (dimensionesOpt.isPresent()) {
            return ResponseEntity.ok(dimensionesOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Dimension dimensiones, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(dimensiones));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Dimension dimensiones, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Dimension> dimensionesOpt = servicio.update(id, dimensiones);
        if (dimensionesOpt.isPresent()) {
            return ResponseEntity.ok(dimensionesOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dimension> eliminar(@PathVariable Long id) {
        Optional<Dimension> dimensionesOpt = servicio.delete(id);
        if (dimensionesOpt.isPresent()) {
            return ResponseEntity.ok(dimensionesOpt.orElseThrow());
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