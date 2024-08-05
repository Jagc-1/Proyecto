package com.jpa.proyecto.domain.services.permiso;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Permiso;

public interface PermisoService {
    List<Permiso> findAll();
    Optional<Permiso> findById(Long id);
    Permiso save(Permiso permiso);
    Optional<Permiso> update(Long id, Permiso permiso);
    Optional<Permiso> delete(Long id);
}
