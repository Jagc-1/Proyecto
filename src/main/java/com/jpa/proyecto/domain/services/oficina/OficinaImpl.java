package com.jpa.proyecto.domain.services.oficina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.OficinaRepository;
import com.jpa.proyecto.persistence.entities.Oficina;

import java.util.List;
import java.util.Optional;

@Service
public class OficinaImpl implements OficinaService {

    @Autowired
    private OficinaRepository repository;

    @Transactional
    @Override
    public List<Oficina> findAll() {
        return (List<Oficina>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Oficina> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Oficina save(Oficina oficina) {
        return repository.save(oficina);
    }

    @Transactional
    @Override
    public Optional<Oficina> update(Long id, Oficina oficina) {
        Optional<Oficina> oficinaOpt = repository.findById(id);
        if (oficinaOpt.isPresent()) {
            Oficina oficinaItem = oficinaOpt.orElseThrow();
           oficinaItem.setContacto(oficina.getContacto());
            oficinaItem.setUbicacion(oficina.getUbicacion());
            
            return Optional.of(repository.save(oficinaItem));
        }
        return oficinaOpt;
    }

    @Transactional
    @Override
    public Optional<Oficina> delete(Long id) {
        Optional<Oficina> oficinaOpt = repository.findById(id);
        oficinaOpt.ifPresent(oficinaItem -> {
            repository.delete(oficinaItem);
        });
        return oficinaOpt;
    }
}
