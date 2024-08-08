package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.pedido.PedidoService;
import com.jpa.proyecto.persistence.entities.Pedido;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService servicio;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> ver(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = servicio.findById(id);
        if (pedidoOpt.isPresent()) {
            return ResponseEntity.ok(pedidoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Pedido pedido, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Pedido pedido, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Pedido> pedidoOpt = servicio.update(id, pedido);
        if (pedidoOpt.isPresent()) {
            return ResponseEntity.ok(pedidoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pedido> eliminar(@PathVariable Long id) {
        Optional<Pedido> pedidoOpt = servicio.delete(id);
        if (pedidoOpt.isPresent()) {
            return ResponseEntity.ok(pedidoOpt.orElseThrow());
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

    @GetMapping("/pedidoEstado")
    public ResponseEntity<List<Pedido>> pedidoEstado(@RequestParam Long estado){
        List<Pedido> listPE = servicio.findPedidoByEstado(estado);
        if (listPE.isEmpty()) {
            return ResponseEntity.ok(listPE);
        }
        return ResponseEntity.notFound().build();
    }
}
