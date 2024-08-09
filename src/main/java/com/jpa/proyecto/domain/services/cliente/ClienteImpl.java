package com.jpa.proyecto.domain.services.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.ClienteRepository;
import com.jpa.proyecto.persistence.entities.Cliente;

import jakarta.transaction.Transactional;

@Service
public class ClienteImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Transactional
    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional
    @Override
    public Optional<Cliente> update(Long id, Cliente cliente) {
        Optional<Cliente> clienteOpt = repository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente clienteItem = clienteOpt.orElseThrow();
            clienteItem.setNombre(cliente.getNombre());
            clienteItem.setApellido(cliente.getApellido());
            clienteItem.setEmpleado(cliente.getEmpleado());
            clienteItem.setCredito(cliente.getCredito());
            clienteItem.setUbicacion(cliente.getUbicacion());
            return Optional.of(repository.save(clienteItem));
        }
        return clienteOpt;
    }

    @Transactional
    @Override
    public Optional<Cliente> delete(Long id) {
        Optional<Cliente> clienteOpt = repository.findById(id);
        clienteOpt.ifPresent(clienteItem -> {
            repository.delete(clienteItem);
        });
        return clienteOpt;
    }

    @Transactional
    @Override
    public List<Cliente> findCustomersByCity(String nombre){
        return repository.findCustomersByCity(nombre);
    }

    @Transactional
    @Override
    public List<Cliente> findCustomersWithPendingOrders(){
        return repository.findCustomersWithPendingOrders();
    }
}
