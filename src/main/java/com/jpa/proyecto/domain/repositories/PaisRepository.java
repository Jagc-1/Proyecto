package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Pais;

public interface PaisRepository extends CrudRepository<Pais, Long>{

}
