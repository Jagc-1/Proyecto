package com.jpa.proyecto.domain.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.estado.id = :estado")
    List<Pedido> findPedidoByEstado(@Param("estado") Long estado);

    @Query("SELECT p FROM Pedido p WHERE p.fechaPedido BETWEEN :fechaEsperada AND :fechaPedido")
List<Pedido> findOrdersByDateRange(@Param("fechaEsperada") Date fechaEsperada, @Param("fechaPedido") Date fechaPedido);


}
