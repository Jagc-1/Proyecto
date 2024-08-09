package com.jpa.proyecto.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("SELECT c " +
            "FROM Cliente c " +
            "JOIN c.ubicacion u " +
            "JOIN u.ciudad ci " +
            "WHERE ci.nombre = :ciudad")
    List<Cliente> findCustomersByCity(@Param("ciudad") String nombre);

    // PEDIDOS PENDIENTES
    @Query("SELECT DISTINCT c FROM Cliente c " +
            "JOIN Pedido pe ON pe.cliente.id = c.id " +
            "JOIN pe.estado e " +
            "WHERE e.nombre = 'Pendiente'")
    List<Cliente> findCustomersWithPendingOrders();

}
