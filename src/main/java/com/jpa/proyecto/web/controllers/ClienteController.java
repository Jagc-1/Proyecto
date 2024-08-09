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

import com.jpa.proyecto.domain.services.cliente.ClienteService;
import com.jpa.proyecto.persistence.entities.Cliente;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService servicio;

    @GetMapping
    public List<Cliente> listarClientes() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> ver(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = servicio.findById(id);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Cliente cliente, BindingResult resultado) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Cliente cliente, BindingResult resultado,
            @PathVariable Long id) {
        if (resultado.hasFieldErrors()) {
            return validar(resultado);
        }
        Optional<Cliente> clienteOpt = servicio.update(id, cliente);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> eliminar(@PathVariable Long id) {
        Optional<Cliente> clienteOpt = servicio.delete(id);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.orElseThrow());
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

    @GetMapping("/ciudadCliente")
    public ResponseEntity<List<Cliente>> clienteCiudad(@RequestParam String nombre) {
        List<Cliente> listCC = servicio.findCustomersByCity(nombre);
        if (listCC.isEmpty()) {
            return ResponseEntity.ok(listCC);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pendiente")
    public ResponseEntity<List<Cliente>> pedidoPendiente() {
        List<Cliente> listPC = servicio.findCustomersWithPendingOrders();
        if (listPC.isEmpty()) {
            return ResponseEntity.ok(listPC);
        }
        return ResponseEntity.notFound().build();
    }
}
