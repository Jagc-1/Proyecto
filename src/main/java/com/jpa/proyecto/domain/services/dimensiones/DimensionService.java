package com.jpa.proyecto.domain.services.dimensiones;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Dimension;

public interface DimensionService {
    List<Dimension> findAll();
    Optional<Dimension> findById(Long id);
    Dimension save(Dimension dimensiones);
    Optional<Dimension> update(Long id, Dimension dimensiones);
    Optional<Dimension> delete(Long id);
}
