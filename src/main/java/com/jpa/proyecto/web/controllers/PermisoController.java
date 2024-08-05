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

import com.jpa.proyecto.domain.services.permiso.PermisoService;
import com.jpa.proyecto.persistence.entities.Permiso;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/permisos")
public class PermisoController {

 @Autowired
    private PermisoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Permiso> listRolePermission(){
        return this.service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Permiso> view(@PathVariable Long id){
        Optional<Permiso> optionalRolePermission  = service.findById(id);
        if (optionalRolePermission.isPresent()){
            return ResponseEntity.ok(optionalRolePermission.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody Permiso role_permission, BindingResult result){
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(role_permission));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<Permiso> update(@PathVariable Long id, @Valid @RequestBody Permiso role_permission){
        Optional<Permiso> role_permissionOptional = this.service.update(id, role_permission);
        if (role_permissionOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(role_permissionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Permiso> delete(@PathVariable Long id){
        //Permiso role_permission = new Permiso();
        //role_permission.setId(id);
        Optional<Permiso> optionalRolePermission;
     optionalRolePermission = this.service.delete(id);
        if (optionalRolePermission.isPresent()){
            return ResponseEntity.ok(optionalRolePermission.orElseThrow());
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
