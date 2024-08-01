package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.estado.EstadoService;
import com.jpa.proyecto.persistence.entities.Estado;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoService servicio;

    @GetMapping
    public List<Estado> listarEstados() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> ver(@PathVariable Long id) {
        Optional<Estado> estadoOpt = servicio.findById(id);
        if (estadoOpt.isPresent()) {
            return ResponseEntity.ok(estadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Estado estado, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Estado estado, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Estado> estadoOpt = servicio.update(id, estado);
        if (estadoOpt.isPresent()) {
            return ResponseEntity.ok(estadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> eliminar(@PathVariable Long id) {
        Optional<Estado> estadoOpt = servicio.delete(id);
        if (estadoOpt.isPresent()) {
            return ResponseEntity.ok(estadoOpt.orElseThrow());
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
