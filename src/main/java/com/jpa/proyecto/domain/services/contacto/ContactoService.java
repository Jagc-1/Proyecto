package com.jpa.proyecto.domain.services.contacto;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Contacto;

public interface ContactoService {
        List<Contacto> findAll();
    Optional<Contacto> findById(Long id);
    Contacto save(Contacto contacto);
    Optional<Contacto> update(Long id, Contacto contacto);
    Optional<Contacto> delete(Long id);
}
