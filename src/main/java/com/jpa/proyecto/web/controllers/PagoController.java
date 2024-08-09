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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.proyecto.domain.services.pago.PagoService;
import com.jpa.proyecto.persistence.entities.Pago;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService servicio;

    @GetMapping
    public List<Pago> listarPagos() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> ver(@PathVariable Long id) {
        Optional<Pago> pagoOpt = servicio.findById(id);
        if (pagoOpt.isPresent()) {
            return ResponseEntity.ok(pagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Pago pago, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Pago pago, BindingResult resultado, @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Pago> pagoOpt = servicio.update(id, pago);
        if (pagoOpt.isPresent()) {
            return ResponseEntity.ok(pagoOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pago> eliminar(@PathVariable Long id) {
        Optional<Pago> pagoOpt = servicio.delete(id);
        if (pagoOpt.isPresent()) {
            return ResponseEntity.ok(pagoOpt.orElseThrow());
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

    @GetMapping("/pagoCliente")
    public ResponseEntity<List<Pago>> productoGama(@RequestParam Long cliente){
        List<Pago> listPC = servicio.findPagosByCliente(cliente);
        if (listPC.isEmpty()) {
            return ResponseEntity.ok(listPC);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/PagoMetodo")
    public ResponseEntity<List<Pago>> metodoPago(@RequestParam String metodo_pago){
        List<Pago> listPM = servicio.findPaymentsByMetodoPago(metodo_pago);
        if(listPM.isEmpty()){
            return ResponseEntity.ok(listPM);
        }
        return ResponseEntity.notFound().build();
    }
}