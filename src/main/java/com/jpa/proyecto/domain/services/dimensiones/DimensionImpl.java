package com.jpa.proyecto.domain.services.dimensiones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.DimensionRepository;
import com.jpa.proyecto.persistence.entities.Dimension;

import java.util.List;
import java.util.Optional;

@Service
public class DimensionImpl implements DimensionService {

    @Autowired
    private DimensionRepository repository;

    @Transactional
    @Override
    public List<Dimension> findAll() {
        return (List<Dimension>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Dimension> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Dimension save(Dimension dimensiones) {
        return repository.save(dimensiones);
    }

    @Transactional
    @Override
    public Optional<Dimension> update(Long id, Dimension dimensiones) {
        Optional<Dimension> dimensionesOpt = repository.findById(id);
        if (dimensionesOpt.isPresent()) {
            Dimension dimensionesItem = dimensionesOpt.orElseThrow();
            dimensionesItem.setAlto(dimensiones.getAlto());
            dimensionesItem.setAncho(dimensiones.getAncho());
            dimensionesItem.setLargo(dimensiones.getLargo());
            dimensionesItem.setPeso(dimensiones.getPeso());
            return Optional.of(repository.save(dimensionesItem));
        }
        return dimensionesOpt;
    }

    @Transactional
    @Override
    public Optional<Dimension> delete(Long id) {
        Optional<Dimension> dimensionesOpt = repository.findById(id);
        dimensionesOpt.ifPresent(dimensionesItem -> {
            repository.delete(dimensionesItem);
        });
        return dimensionesOpt;
    }
}