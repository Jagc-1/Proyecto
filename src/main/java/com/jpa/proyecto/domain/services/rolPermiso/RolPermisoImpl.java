package com.jpa.proyecto.domain.services.rolPermiso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.RolPermisoRepository;
import com.jpa.proyecto.persistence.entities.RolPermiso;

import jakarta.transaction.Transactional;

@Service
public class RolPermisoImpl implements RolPermisoService {

    @Autowired
    RolPermisoRepository repository;
    
    @Transactional
    @Override
    public Optional<RolPermiso> delete(Long id) {
        Optional<RolPermiso> rolepermisoOpt = this.repository.findById(id);
        rolepermisoOpt.ifPresent(
            RolePermissionFound -> {
                this.repository.delete(RolePermissionFound);
            }
        );
        return rolepermisoOpt;
    }
    
    @Transactional
    @Override
    public List<RolPermiso> findAll() {
        return (List<RolPermiso>) this.repository.findAll();
    }

    @Transactional
    @Override
    public Optional<RolPermiso> findById(Long id) {
        return this.repository.findById(id);
    }

    @Transactional
    @Override
    public RolPermiso save(RolPermiso RolPermiso) {
        return this.repository.save(RolPermiso);
    }

    @Transactional
    @Override
    public Optional<RolPermiso> update(Long id, RolPermiso rolepermiso) {
        Optional<RolPermiso> rolepermisoOpt = this.repository.findById(id);
        if (rolepermisoOpt.isPresent()) {
            RolPermiso rolpermisoItem = rolepermisoOpt.orElseThrow();
            rolpermisoItem.setRol(rolepermiso.getRol());
            rolpermisoItem.setPermiso(rolepermiso.getPermiso());
            return Optional.of(this.repository.save(rolpermisoItem));
        }
        return rolepermisoOpt;
    }
}

