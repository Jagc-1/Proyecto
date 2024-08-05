package com.jpa.proyecto.domain.services.cuenta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.CuentaRepository;
import com.jpa.proyecto.persistence.entities.Cuenta;

import jakarta.transaction.Transactional;

@Service
public class CuentaImpl implements CuentaService{   


    @Autowired
    CuentaRepository repository;

    @Transactional
    @Override
    public List<Cuenta> findAll() {
        return (List<Cuenta>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Cuenta> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Cuenta save(Cuenta cuenta) {
        return repository.save(cuenta);
    }

    @Override
    public Optional<Cuenta> update(Long id, Cuenta cuenta) {
        Optional<Cuenta> cuentaOpt = repository.findById(id);
        if (cuentaOpt.isPresent()) {
            Cuenta cuentaItem = cuentaOpt.orElseThrow();
            cuentaItem.setNombre(cuenta.getNombre());
            cuentaItem.setContrasena(cuenta.getContrasena());
            return Optional.of(repository.save(cuentaItem));
        }
        return cuentaOpt;
    }

    @Transactional
    @Override
    public Optional<Cuenta> delete(Long id) {
        Optional<Cuenta> cuentaOpt = repository.findById(id);
        cuentaOpt.ifPresent(cuenta ->{
            repository.delete(cuenta);
        });
        return cuentaOpt;
    }

}
