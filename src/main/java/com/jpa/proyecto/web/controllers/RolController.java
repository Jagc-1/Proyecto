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

import com.jpa.proyecto.domain.services.rol.RolService;
import com.jpa.proyecto.persistence.entities.Rol;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rol")
public class RolController {
     @Autowired
    private RolService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Rol> listRole(){
        return this.roleService.findAll();
    }

    @GetMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Rol> view(@PathVariable Long id){
        Optional<Rol> optionalRole  = roleService.findById(id);
        if (optionalRole.isPresent()){
            return ResponseEntity.ok(optionalRole.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Rol role, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(role));
    }

    @PutMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Rol> update(@PathVariable Long id, @Valid @RequestBody Rol role){
        Optional<Rol> roleOptional = this.roleService.update(id, role);
        if (roleOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(roleOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/<built-in function id>")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Rol> delete(@PathVariable Long id){
        //Rol role = new Rol();
        //role.setId(id);
        Optional<Rol> optionalRole = this.roleService.delete(id);
        if (optionalRole.isPresent()){
            return ResponseEntity.ok(optionalRole.orElseThrow());
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
