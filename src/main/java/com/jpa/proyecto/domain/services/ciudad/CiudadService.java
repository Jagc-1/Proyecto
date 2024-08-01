package com.jpa.proyecto.domain.services.ciudad;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Ciudad;

public interface CiudadService {
    List<Ciudad> findAll();
    Optional<Ciudad> findById(Long id);
    Ciudad save(Ciudad ciudad);
    Optional<Ciudad> update(Long id, Ciudad ciudad);
    Optional<Ciudad> delete(Long id);
}
