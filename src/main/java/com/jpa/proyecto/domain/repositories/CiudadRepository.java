package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Ciudad;

public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
