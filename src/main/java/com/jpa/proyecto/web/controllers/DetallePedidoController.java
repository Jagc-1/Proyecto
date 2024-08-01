package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.detallePedido.DetallePedidoService;
import com.jpa.proyecto.persistence.entities.DetallePedido;

import jakarta.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/detalles-pedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService servicio;

    @GetMapping
    public List<DetallePedido> listarDetallesPedidos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedido> ver(@PathVariable Long id) {
        Optional<DetallePedido> detallePedidoOpt = servicio.findById(id);
        if (detallePedidoOpt.isPresent()) {
            return ResponseEntity.ok(detallePedidoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody DetallePedido detallePedido, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(detallePedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody DetallePedido detallePedido, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<DetallePedido> detallePedidoOpt = servicio.update(id, detallePedido);
        if (detallePedidoOpt.isPresent()) {
            return ResponseEntity.ok(detallePedidoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DetallePedido> eliminar(@PathVariable Long id) {
        Optional<DetallePedido> detallePedidoOpt = servicio.delete(id);
        if (detallePedidoOpt.isPresent()) {
            return ResponseEntity.ok(detallePedidoOpt.orElseThrow());
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