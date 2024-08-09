package com.jpa.proyecto.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    // Caso de Uso 7: Consultar Productos por Gama
    @Query("SELECT p FROM Producto p WHERE p.gamaProducto = :gamaProducto")
    List<Producto> findProductsByGama(@Param("gamaProducto") Long gamaProducto);

    // Caso de Uso 12: Consultar Productos con Bajo Stock
    @Query("SELECT p " +
            "FROM Producto p " +
            "JOIN p.stock s " +
            "WHERE s.stock_actual < :stockMinimo")
    List<Producto> findProductsWithLowStock(@Param("stockMinimo") int stockMinimo);

}
