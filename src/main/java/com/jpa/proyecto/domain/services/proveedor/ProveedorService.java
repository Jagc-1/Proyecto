package com.jpa.proyecto.domain.services.proveedor;

import java.util.List;
import java.util.Optional;
import com.jpa.proyecto.persistence.entities.Proveedor;

public interface ProveedorService {

    List<Proveedor> findAll();
    Optional<Proveedor> findById(Long id);
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> update(Long id, Proveedor proveedor);
    Optional<Proveedor> delete(Long id);
}
