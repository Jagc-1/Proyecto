package com.jpa.proyecto.domain.services.rolPermiso;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.RolPermiso;

public interface RolPermisoService {
    List<RolPermiso> findAll();
    Optional<RolPermiso> findById(Long id);
    RolPermiso save(RolPermiso rolPermiso);
    Optional<RolPermiso> update(Long id, RolPermiso rolPermiso);
    Optional<RolPermiso> delete(Long id);

}
