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

import com.jpa.proyecto.domain.services.cuentarol.CuentaRolService;
import com.jpa.proyecto.persistence.entities.CuentaRol;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuenta-rol")
public class CuentaRolController {
      @Autowired
    private CuentaRolService service;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<CuentaRol> listAccountRole(){
        return this.service.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<CuentaRol> view(@PathVariable Long id){
        Optional<CuentaRol> optionalAccountRole  = service.findById(id);
        if (optionalAccountRole.isPresent()){
            return ResponseEntity.ok(optionalAccountRole.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody CuentaRol account_role, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(account_role));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<CuentaRol> update(@PathVariable Long id, @Valid @RequestBody CuentaRol account_role){
        Optional<CuentaRol> account_roleOptional = this.service.update(id, account_role);
        if (account_roleOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(account_roleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<CuentaRol> delete(@PathVariable Long id){
        //CuentaRol account_role = new CuentaRol();
        //account_role.setId(id);
        Optional<CuentaRol> optionalAccountRole = this.service.delete(id);
        if (optionalAccountRole.isPresent()){
            return ResponseEntity.ok(optionalAccountRole.orElseThrow());
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
