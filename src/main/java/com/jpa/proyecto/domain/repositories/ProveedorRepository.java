package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Proveedor;

public interface ProveedorRepository extends CrudRepository<Proveedor, Long>{

}
