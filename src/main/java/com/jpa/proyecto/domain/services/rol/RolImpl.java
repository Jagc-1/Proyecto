package com.jpa.proyecto.domain.services.rol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.proyecto.domain.repositories.RolRepository;
import com.jpa.proyecto.persistence.entities.Rol;

import jakarta.transaction.Transactional;

@Service
public class RolImpl implements RolService{

    @Autowired
    private RolRepository repository;

    @Transactional
    @Override
    public List<Rol> findAll() {
        return (List<Rol>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Rol> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Rol save(Rol rol) {
        return repository.save(rol);
    }

    @Transactional
    @Override
    public Optional<Rol> update(Long id, Rol rol) {
        Optional<Rol> rolOpt = repository.findById(id);
        if (rolOpt.isPresent()) {
            Rol rolItem = rolOpt.orElseThrow();
            rolItem.setNombre(rol.getNombre());
            return  Optional.of(repository.save(rolItem));
        }
        return rolOpt;
    }

    @Transactional
    @Override
    public Optional<Rol> delete(Long id) {
        Optional<Rol> rolOpt = repository.findById(id);
        rolOpt.ifPresent(rolItem ->{
            repository.delete(rolItem);
        });
        return rolOpt;
    }
    
}
