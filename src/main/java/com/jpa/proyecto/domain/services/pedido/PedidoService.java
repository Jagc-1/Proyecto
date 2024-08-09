package com.jpa.proyecto.domain.services.pedido;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Pedido;

public interface PedidoService {
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Pedido save(Pedido pedido);
    Optional<Pedido> update(Long id, Pedido pedido);
    Optional<Pedido> delete(Long id);
    List<Pedido> findPedidoByEstado(Long estado);
    List<Pedido> findOrdersByDateRange(Date fecha_esperada,Date fecha_pedido);
}
