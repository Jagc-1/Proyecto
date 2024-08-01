package com.jpa.proyecto.domain.services.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.EstadoRepository;
import com.jpa.proyecto.persistence.entities.Estado;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoImpl implements EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Transactional
    @Override
    public List<Estado> findAll() {
        return (List<Estado>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Estado> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Estado save(Estado estado) {
        return repository.save(estado);
    }

    @Transactional
    @Override
    public Optional<Estado> update(Long id, Estado estado) {
        Optional<Estado> estadoOpt = repository.findById(id);
        if (estadoOpt.isPresent()) {
            Estado estadoItem = estadoOpt.orElseThrow();
            estadoItem.setNombre(estado.getNombre());
            return Optional.of(repository.save(estadoItem));
        }
        return estadoOpt;
    }

    @Transactional
    @Override
    public Optional<Estado> delete(Long id) {
        Optional<Estado> estadoOpt = repository.findById(id);
        estadoOpt.ifPresent(estadoItem -> {
            repository.delete(estadoItem);
        });
        return estadoOpt;
    }
}
