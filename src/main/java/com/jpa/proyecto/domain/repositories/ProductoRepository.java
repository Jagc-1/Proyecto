package com.jpa.proyecto.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.jpa.proyecto.persistence.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    // Caso de Uso 7: Consultar Productos por Gama
    @Query("SELECT p FROM Producto p WHERE p.gamaProducto = :gamaProducto")
    List<Producto> findProductsByGama(@Param("gamaProducto") Long gamaProducto);
}
