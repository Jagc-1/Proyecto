package com.jpa.proyecto.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.proveedor.ProveedorService;
import com.jpa.proyecto.persistence.entities.Proveedor;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService servicio;

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> ver(@PathVariable Long id) {
        Optional<Proveedor> proveedorOpt = servicio.findById(id);
        if (proveedorOpt.isPresent()) {
            return ResponseEntity.ok(proveedorOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Proveedor proveedor, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Proveedor proveedor, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Proveedor> proveedorOpt = servicio.update(id, proveedor);
        if (proveedorOpt.isPresent()) {
            return ResponseEntity.ok(proveedorOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Proveedor> eliminar(@PathVariable Long id) {
        Optional<Proveedor> proveedorOpt = servicio.delete(id);
        if (proveedorOpt.isPresent()) {
            return ResponseEntity.ok(proveedorOpt.orElseThrow());
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