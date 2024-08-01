package com.jpa.proyecto.domain.services.clienteDireccion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.ClienteDireccionRepository;
import com.jpa.proyecto.persistence.entities.ClienteDireccion;


@Service
public class ClienteDireccionImpl implements ClienteDireccionService {

    @Autowired
    private ClienteDireccionRepository repository;

    @Transactional
    @Override
    public List<ClienteDireccion> findAll() {
        return (List<ClienteDireccion>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<ClienteDireccion> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public ClienteDireccion save(ClienteDireccion direccionCliente) {
        return repository.save(direccionCliente);
    }

    @Transactional
    @Override
    public Optional<ClienteDireccion> update(Long id, ClienteDireccion direccionCliente) {
        Optional<ClienteDireccion> direccionOpt = repository.findById(id);
        if (direccionOpt.isPresent()) {
            ClienteDireccion direccionItem = direccionOpt.orElseThrow();
            direccionItem.setUbicacion(direccionCliente.getUbicacion());
            direccionItem.setContacto(direccionCliente.getContacto());
            
            return Optional.of(repository.save(direccionItem));
        }
        return direccionOpt;
    }

    @Transactional
    @Override
    public Optional<ClienteDireccion> delete(Long id) {
        Optional<ClienteDireccion> direccionOpt = repository.findById(id);
        direccionOpt.ifPresent(direccionItem -> {
            repository.delete(direccionItem);
        });
        return direccionOpt;
    }
}