package com.jpa.proyecto.domain.services.cuentarol;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.CuentaRolRepository;
import com.jpa.proyecto.persistence.entities.CuentaRol;

@Service
public class CuentaRolImpl implements CuentaRolService {

  @Autowired
    private CuentaRolRepository repository;

    @Transactional
    @Override
    public List<CuentaRol> findAll() {
        return (List<CuentaRol>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<CuentaRol> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public CuentaRol save(CuentaRol contacto) {
        return repository.save(contacto);
    }

    @Transactional
    @Override
    public Optional<CuentaRol> update(Long id, CuentaRol contacto) {
        Optional<CuentaRol> cuentarolOpt = repository.findById(id);
        if (cuentarolOpt.isPresent()) {
            CuentaRol cuentaolItem = cuentarolOpt.orElseThrow();
            
            return Optional.of(repository.save(cuentaolItem));
        }
        return cuentarolOpt;
    }

    @Transactional
    @Override
    public Optional<CuentaRol> delete(Long id) {
        Optional<CuentaRol> cuentarolOpt = repository.findById(id);
        cuentarolOpt.ifPresent(cuentaolItem -> {
            repository.delete(cuentaolItem);
        });
        return cuentarolOpt;
    }

}
