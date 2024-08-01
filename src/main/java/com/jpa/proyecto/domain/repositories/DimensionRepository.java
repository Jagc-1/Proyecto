package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.proyecto.persistence.entities.Dimension;

public interface DimensionRepository extends CrudRepository<Dimension, Long> {

}
