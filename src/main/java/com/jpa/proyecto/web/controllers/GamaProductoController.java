package com.jpa.proyecto.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.gamaProducto.GamaProductoService;
import com.jpa.proyecto.persistence.entities.GamaProducto;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/gama-productos")
public class GamaProductoController {

    @Autowired
    private GamaProductoService servicio;

    @GetMapping
    public List<GamaProducto> listarGamaProductos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamaProducto> ver(@PathVariable Long id) {
        Optional<GamaProducto> gamaProductoOpt = servicio.findById(id);
        if (gamaProductoOpt.isPresent()) {
            return ResponseEntity.ok(gamaProductoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody GamaProducto gamaProducto, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(gamaProducto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody GamaProducto gamaProducto, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<GamaProducto> gamaProductoOpt = servicio.update(id, gamaProducto);
        if (gamaProductoOpt.isPresent()) {
            return ResponseEntity.ok(gamaProductoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GamaProducto> eliminar(@PathVariable Long id) {
        Optional<GamaProducto> gamaProductoOpt = servicio.delete(id);
        if (gamaProductoOpt.isPresent()) {
            return ResponseEntity.ok(gamaProductoOpt.orElseThrow());
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