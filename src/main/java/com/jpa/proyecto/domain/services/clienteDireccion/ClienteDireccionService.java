package com.jpa.proyecto.domain.services.clienteDireccion;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.ClienteDireccion;

public interface ClienteDireccionService {
    List<ClienteDireccion> findAll();
    Optional<ClienteDireccion> findById(Long id);
    ClienteDireccion save(ClienteDireccion clienteDireccion);
    Optional<ClienteDireccion> update(Long id, ClienteDireccion direccionCliente);
    Optional<ClienteDireccion> delete(Long id);
}
