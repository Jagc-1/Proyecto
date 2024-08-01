package com.jpa.proyecto.domain.services.gamaProducto;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.GamaProducto;

public interface GamaProductoService {
    List<GamaProducto> findAll();
    Optional<GamaProducto> findById(Long id);
    GamaProducto save(GamaProducto gamaProducto);
    Optional<GamaProducto> update(Long id, GamaProducto gamaProducto);
    Optional<GamaProducto> delete(Long id);
}
