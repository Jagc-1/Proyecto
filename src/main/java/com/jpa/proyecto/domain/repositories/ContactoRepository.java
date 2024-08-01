package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.proyecto.persistence.entities.Contacto;

public interface ContactoRepository extends CrudRepository<Contacto, Long>{

}
