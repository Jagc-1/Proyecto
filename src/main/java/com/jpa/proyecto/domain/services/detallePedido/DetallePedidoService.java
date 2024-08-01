package com.jpa.proyecto.domain.services.detallePedido;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.DetallePedido;

public interface DetallePedidoService {
    List<DetallePedido> findAll();
    Optional<DetallePedido> findById(Long id);
    DetallePedido save(DetallePedido detallePedido);
    Optional<DetallePedido> update(Long id, DetallePedido detallePedido);
    Optional<DetallePedido> delete(Long id);
}
