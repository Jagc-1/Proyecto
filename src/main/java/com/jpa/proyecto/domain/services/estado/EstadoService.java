package com.jpa.proyecto.domain.services.estado;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Estado;

public interface EstadoService {
    List<Estado> findAll();
    Optional<Estado> findById(Long id);
    Estado save(Estado estado);
    Optional<Estado> update(Long id, Estado estado);   
    Optional<Estado> delete(Long id);
}
