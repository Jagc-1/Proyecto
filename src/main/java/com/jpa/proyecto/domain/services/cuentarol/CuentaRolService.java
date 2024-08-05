package com.jpa.proyecto.domain.services.cuentarol;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.CuentaRol;

public interface CuentaRolService {

    List<CuentaRol> findAll();
    Optional<CuentaRol> findById(Long id);
    CuentaRol save(CuentaRol contacto);
    Optional<CuentaRol> update(Long id, CuentaRol contacto);
    Optional<CuentaRol> delete(Long id);
}
