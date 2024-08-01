package com.jpa.proyecto.domain.services.credito;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Credito;

public interface CreditoService {
    List<Credito> findAll();
    Optional<Credito> findById(Long id);
    Credito save(Credito credito);
    Optional<Credito> update(Long id, Credito credito);
    Optional<Credito> delete(Long id);
}
