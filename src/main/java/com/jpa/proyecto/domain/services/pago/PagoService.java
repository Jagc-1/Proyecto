package com.jpa.proyecto.domain.services.pago;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Pago;

public interface PagoService {

    List<Pago> findAll();
    Optional<Pago> findById(Long id);
    Pago save(Pago pago);
    Optional<Pago> update(Long id, Pago pago);
    Optional<Pago> delete(Long id);
}
