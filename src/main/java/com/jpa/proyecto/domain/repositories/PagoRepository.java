package com.jpa.proyecto.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.jpa.proyecto.persistence.entities.Pago;

public interface PagoRepository extends CrudRepository<Pago, Long>{
    @Query("SELECT p " +
           "FROM Pago p " +
           "JOIN p.pedido pe " +
           "WHERE pe.cliente.id = :clienteId")
    List<Pago> findPagosByCliente(@Param("clienteId") Long cliente);
}
