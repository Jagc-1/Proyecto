package com.jpa.proyecto.domain.services.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.ProveedorRepository;
import com.jpa.proyecto.persistence.entities.Proveedor;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository repository;

    @Transactional
    @Override
    public List<Proveedor> findAll() {
        return (List<Proveedor>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Proveedor> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Proveedor save(Proveedor proveedor) {
        return repository.save(proveedor);
    }

    @Transactional
    @Override
    public Optional<Proveedor> update(Long id, Proveedor proveedor) {
        Optional<Proveedor> proveedorOpt = repository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedorItem = proveedorOpt.orElseThrow();
            proveedorItem.setNombres(proveedor.getNombres());
            proveedorItem.setApellidos(proveedor.getApellidos());
            proveedorItem.setNombre_empresa(proveedor.getNombre_empresa());
            proveedorItem.setUbicacion(proveedor.getUbicacion());
            return Optional.of(repository.save(proveedorItem));
        }
        return proveedorOpt;
    }

    @Transactional
    @Override
    public Optional<Proveedor> delete(Long id) {
        Optional<Proveedor> proveedorOpt = repository.findById(id);
        proveedorOpt.ifPresent(proveedorItem -> {
            repository.delete(proveedorItem);
        });
        return proveedorOpt;
    }
}
