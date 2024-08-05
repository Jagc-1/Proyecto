package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.proyecto.persistence.entities.Permiso;

public interface PermisoRepository  extends  CrudRepository<Permiso, Long>{

}
