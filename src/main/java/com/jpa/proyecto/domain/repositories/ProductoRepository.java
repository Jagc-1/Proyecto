package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
