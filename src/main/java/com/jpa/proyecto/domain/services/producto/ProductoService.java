package com.jpa.proyecto.domain.services.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Producto;

public interface ProductoService {

    List<Producto> findAll();

    Optional<Producto> findById(Long id);

    Producto save(Producto producto);

    Optional<Producto> update(Long id, Producto producto);

    Optional<Producto> delete(Long id);

    List<Product> findProductsByGama(Long gamaProducto);
}
