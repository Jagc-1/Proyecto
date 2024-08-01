package com.jpa.proyecto.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.proyecto.domain.services.clienteDireccion.ClienteDireccionService;
import com.jpa.proyecto.persistence.entities.ClienteDireccion;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes-direcciones")
public class ClienteDireccionController {

    @Autowired
    private ClienteDireccionService servicio;

    @GetMapping
    public List<ClienteDireccion> listarClientesDirecciones() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDireccion> ver(@PathVariable Long id) {
        Optional<ClienteDireccion> clienteDireccionOpt = servicio.findById(id);
        if (clienteDireccionOpt.isPresent()) {
            return ResponseEntity.ok(clienteDireccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ClienteDireccion clienteDireccion, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(clienteDireccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody ClienteDireccion clienteDireccion, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<ClienteDireccion> clienteDireccionOpt = servicio.update(id, clienteDireccion);
        if (clienteDireccionOpt.isPresent()) {
            return ResponseEntity.ok(clienteDireccionOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDireccion> eliminar(@PathVariable Long id) {
        Optional<ClienteDireccion> clienteDireccionOpt = servicio.delete(id);
        if (clienteDireccionOpt.isPresent()) {
            return ResponseEntity.ok(clienteDireccionOpt.orElseThrow());
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

