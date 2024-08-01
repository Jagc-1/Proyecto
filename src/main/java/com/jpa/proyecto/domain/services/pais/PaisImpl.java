package com.jpa.proyecto.domain.services.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.PaisRepository;
import com.jpa.proyecto.persistence.entities.Pais;

import java.util.List;
import java.util.Optional;

@Service
public class PaisImpl implements PaisService {

    @Autowired
    private PaisRepository repository;

    @Transactional
    @Override
    public List<Pais> findAll() {
        return (List<Pais>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Pais> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Pais save(Pais pais) {
        return repository.save(pais);
    }

    @Transactional
    @Override
    public Optional<Pais> update(Long id, Pais pais) {
        Optional<Pais> paisOpt = repository.findById(id);
        if (paisOpt.isPresent()) {
            Pais paisItem = paisOpt.orElseThrow();
            paisItem.setNombre(pais.getNombre());
            return Optional.of(repository.save(paisItem));
        }
        return paisOpt;
    }

    @Transactional
    @Override
    public Optional<Pais> delete(Long id) {
        Optional<Pais> paisOpt = repository.findById(id);
        paisOpt.ifPresent(paisItem -> {
            repository.delete(paisItem);
        });
        return paisOpt;
    }
}
