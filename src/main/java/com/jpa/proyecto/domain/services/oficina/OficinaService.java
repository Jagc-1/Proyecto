package com.jpa.proyecto.domain.services.oficina;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Oficina;

public interface OficinaService {
    List<Oficina> findAll();
    Optional<Oficina> findById(Long id);
    Oficina save(Oficina oficina);
    Optional<Oficina> update(Long id, Oficina oficina);
    Optional<Oficina> delete(Long id);
}

