package com.jpa.proyecto.domain.services.cuenta;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Cuenta;


public interface CuentaService {
    List<Cuenta> findAll();
    Optional<Cuenta> findById(Long id);
    Cuenta save(Cuenta detallePedido);
    Optional<Cuenta> update(Long id, Cuenta detallePedido);
    Optional<Cuenta> delete(Long id);
}
