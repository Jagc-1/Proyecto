package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.empleado.EmpleadoService;
import com.jpa.proyecto.persistence.entities.Empleado;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService servicio;

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> ver(@PathVariable Long id) {
        Optional<Empleado> empleadoOpt = servicio.findById(id);
        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Empleado empleado, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Empleado empleado, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Empleado> empleadoOpt = servicio.update(id, empleado);
        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> eliminar(@PathVariable Long id) {
        Optional<Empleado> empleadoOpt = servicio.delete(id);
        if (empleadoOpt.isPresent()) {
            return ResponseEntity.ok(empleadoOpt.orElseThrow());
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

    @GetMapping("/empleadoOficina")
    public ResponseEntity<List<Empleado>> empleadoOficina(@RequestParam Long oficinaId){
        List<Empleado> listEO = servicio.findEmployeesByOficina(oficinaId);
        if (listEO.isEmpty()) {
            return ResponseEntity.ok(listEO);
        }
        return ResponseEntity.notFound().build();
    }
}