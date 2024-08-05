package com.jpa.proyecto.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.proyecto.domain.services.cuenta.CuentaService;
import com.jpa.proyecto.persistence.entities.Cuenta;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired 
    private CuentaService service;

     @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Cuenta> listAccount(){
        return this.service.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Cuenta> view(@PathVariable Long id){
        Optional<Cuenta> optionalAccount  = service.findById(id);
        if (optionalAccount.isPresent()){
            return ResponseEntity.ok(optionalAccount.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Cuenta account, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(account));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Cuenta> update(@PathVariable Long id, @Valid @RequestBody Cuenta account){
        Optional<Cuenta> accountOptional = this.service.update(id, account);
        if (accountOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(accountOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Cuenta> delete(@PathVariable Long id){
        //Cuenta account = new Cuenta();
        //account.setId(id);
        Optional<Cuenta> optionalAccount = this.service.delete(id);
        if (optionalAccount.isPresent()){
            return ResponseEntity.ok(optionalAccount.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
