package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.region.RegionService;
import com.jpa.proyecto.persistence.entities.Region;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    @Autowired
    private RegionService servicio;

    @GetMapping
    public List<Region> listarRegiones() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> ver(@PathVariable Long id) {
        Optional<Region> regionOpt = servicio.findById(id);
        if (regionOpt.isPresent()) {
            return ResponseEntity.ok(regionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Region region, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Region region, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Region> regionOpt = servicio.update(id, region);
        if (regionOpt.isPresent()) {
            return ResponseEntity.ok(regionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Region> eliminar(@PathVariable Long id) {
        Optional<Region> regionOpt = servicio.delete(id);
        if (regionOpt.isPresent()) {
            return ResponseEntity.ok(regionOpt.orElseThrow());
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