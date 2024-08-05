package com.jpa.proyecto.domain.services.rol;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Rol;

public interface RolService {
    List<Rol> findAll();
    Optional<Rol> findById(Long id);
    Rol save(Rol credito);
    Optional<Rol> update(Long id, Rol credito);
    Optional<Rol> delete(Long id);
}
