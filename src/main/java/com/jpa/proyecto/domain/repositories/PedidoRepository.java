package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

}
