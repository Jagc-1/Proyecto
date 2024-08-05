package com.jpa.proyecto.domain.services.permiso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.PermisoRepository;
import com.jpa.proyecto.persistence.entities.Permiso;

import jakarta.transaction.Transactional;

@Service
public class PermisoImpl implements PermisoService {
    
    @Autowired
    PermisoRepository repository;
    
    @Transactional
    @Override
    public Optional<Permiso> delete(Long id) {
        Optional<Permiso> optionalRolePermission = this.repository.findById(id);
        optionalRolePermission.ifPresent(
            RolePermissionFound -> {
                this.repository.delete(RolePermissionFound);
            }
        );
        return optionalRolePermission;
    }
    
    @Transactional
    @Override
    public List<Permiso> findAll() {
        return (List<Permiso>) this.repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Permiso> findById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public Permiso save(Permiso RolPermiso) {
        return this.repository.save(RolPermiso);
    }

    @Transactional
    @Override
    public Optional<Permiso> update(Long id, Permiso permiso) {
        Optional<Permiso> optionalPermission = this.repository.findById(id);
        if (optionalPermission.isPresent()) {
            Permiso permissionItem = optionalPermission.orElseThrow();
            permissionItem.setNombre(permiso.getNombre());
            
            return Optional.of(this.repository.save(permissionItem));
        }
        return optionalPermission;
    }
}
