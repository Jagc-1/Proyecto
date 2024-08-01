package com.jpa.proyecto.domain.services.gamaProducto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.GamaProductoRepository;
import com.jpa.proyecto.persistence.entities.GamaProducto;

import java.util.List;
import java.util.Optional;


@Service
public class GamaProductoImpl implements GamaProductoService {

    @Autowired
    private GamaProductoRepository repository;

    @Transactional
    @Override
    public List<GamaProducto> findAll() {
        return (List<GamaProducto>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<GamaProducto> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public GamaProducto save(GamaProducto gamaProducto) {
        return repository.save(gamaProducto);
    }

    @Transactional
    @Override
    public Optional<GamaProducto> update(Long id, GamaProducto gamaProducto) {
        Optional<GamaProducto> gamaProductoOpt = repository.findById(id);
        if (gamaProductoOpt.isPresent()) {
            GamaProducto gamaProductoItem = gamaProductoOpt.orElseThrow();
            gamaProductoItem.setNombre(gamaProducto.getNombre());
            gamaProductoItem.setDescripcion(gamaProducto.getDescripcion());
            gamaProductoItem.setImagen(gamaProducto.getImagen());
            return Optional.of(repository.save(gamaProductoItem));
        }
        return gamaProductoOpt;
    }

    @Transactional
    @Override
    public Optional<GamaProducto> delete(Long id) {
        Optional<GamaProducto> gamaProductoOpt = repository.findById(id);
        gamaProductoOpt.ifPresent(gamaProductoItem -> {
            repository.delete(gamaProductoItem);
        });
        return gamaProductoOpt;
    }
}
