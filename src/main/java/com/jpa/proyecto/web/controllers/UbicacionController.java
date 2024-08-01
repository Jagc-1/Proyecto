package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.ubicacion.UbicacionService;
import com.jpa.proyecto.persistence.entities.Ubicacion;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService servicio;

    @GetMapping
    public List<Ubicacion> listarUbicaciones() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> ver(@PathVariable Long id) {
        Optional<Ubicacion> ubicacionOpt = servicio.findById(id);
        if (ubicacionOpt.isPresent()) {
            return ResponseEntity.ok(ubicacionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Ubicacion ubicacion, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(ubicacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Ubicacion ubicacion, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Ubicacion> ubicacionOpt = servicio.update(id, ubicacion);
        if (ubicacionOpt.isPresent()) {
            return ResponseEntity.ok(ubicacionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ubicacion> eliminar(@PathVariable Long id) {
        Optional<Ubicacion> ubicacionOpt = servicio.delete(id);
        if (ubicacionOpt.isPresent()) {
            return ResponseEntity.ok(ubicacionOpt.orElseThrow());
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
