package com.jpa.proyecto.domain.services.ubicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.UbicacionRepository;
import com.jpa.proyecto.persistence.entities.Ubicacion;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionImpl implements UbicacionService {

    @Autowired
    private UbicacionRepository repository;

    @Transactional
    @Override
    public List<Ubicacion> findAll() {
        return (List<Ubicacion>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Ubicacion> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Ubicacion save(Ubicacion ubicacion) {
        return repository.save(ubicacion);
    }

    @Transactional
    @Override
    public Optional<Ubicacion> update(Long id, Ubicacion ubicacion) {
        Optional<Ubicacion> ubicacionOpt = repository.findById(id);
        if (ubicacionOpt.isPresent()) {
            Ubicacion ubicacionItem = ubicacionOpt.orElseThrow();
            ubicacionItem.setCiudad(ubicacion.getCiudad());
            ubicacionItem.setDireccion1(ubicacion.getDireccion1());
            ubicacionItem.setDireccion2(ubicacion.getDireccion2());
            ubicacionItem.setCodigoPostal(ubicacion.getCodigoPostal());
            return Optional.of(repository.save(ubicacionItem));
        }
        return ubicacionOpt;
    }

    @Transactional
    @Override
    public Optional<Ubicacion> delete(Long id) {
        Optional<Ubicacion> ubicacionOpt = repository.findById(id);
        ubicacionOpt.ifPresent(ubicacionItem -> {
            repository.delete(ubicacionItem);
        });
        return ubicacionOpt;
    }
}
