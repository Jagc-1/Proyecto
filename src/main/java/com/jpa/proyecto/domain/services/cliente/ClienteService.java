package com.jpa.proyecto.domain.services.cliente;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Cliente;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);
    Optional<Cliente> update(Long id, Cliente cliente);
    Optional<Cliente> delete(Long id);
    List<Cliente> findCustomersByCity(String nombre);
    List<Cliente> findCustomersWithPendingOrders();
}
