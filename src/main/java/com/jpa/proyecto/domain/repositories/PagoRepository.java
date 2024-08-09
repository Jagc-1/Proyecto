package com.jpa.proyecto.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Pago;

public interface PagoRepository extends CrudRepository<Pago, Long> {
    @Query("SELECT p " +
            "FROM Pago p " +
            "JOIN p.pedido pe " +
            "WHERE pe.cliente.id = :clienteId")
    List<Pago> findPagosByCliente(@Param("clienteId") Long cliente);

    // PAGO POR METODO DE PAGO
    @Query("SELECT p FROM Pago p " +
            "JOIN p.pedido pe " +
            "WHERE pe.id = p.pedido.id AND pe.metodo_pago = :metodo_pago")
    List<Pago> findPaymentsByMetodoPago(@Param("metodo_pago") String metodo_pago);
}
