package com.jpa.proyecto.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jpa.proyecto.domain.services.stock.StockService;
import com.jpa.proyecto.persistence.entities.Stock;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService servicio;

    @GetMapping
    public List<Stock> listarStocks() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> ver(@PathVariable Long id) {
        Optional<Stock> stockOpt = servicio.findById(id);
        if (stockOpt.isPresent()) {
            return ResponseEntity.ok(stockOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Stock stock, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(stock));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Stock stock, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Stock> stockOpt = servicio.update(id, stock);
        if (stockOpt.isPresent()) {
            return ResponseEntity.ok(stockOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stock> eliminar(@PathVariable Long id) {
        Optional<Stock> stockOpt = servicio.delete(id);
        if (stockOpt.isPresent()) {
            return ResponseEntity.ok(stockOpt.orElseThrow());
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