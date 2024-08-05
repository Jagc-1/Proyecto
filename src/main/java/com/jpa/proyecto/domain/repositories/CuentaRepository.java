package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jpa.proyecto.persistence.entities.Cuenta;

public interface CuentaRepository extends CrudRepository<Cuenta, Long>{
    
}
