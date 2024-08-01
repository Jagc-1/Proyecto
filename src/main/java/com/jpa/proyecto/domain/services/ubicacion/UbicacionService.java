package com.jpa.proyecto.domain.services.ubicacion;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Ubicacion;

public interface UbicacionService {

    List<Ubicacion> findAll();
    Optional<Ubicacion> findById(Long id);
    Ubicacion save(Ubicacion ubicacion);
    Optional<Ubicacion> update(Long id, Ubicacion ubicacion);
    Optional<Ubicacion> delete(Long id);
}
