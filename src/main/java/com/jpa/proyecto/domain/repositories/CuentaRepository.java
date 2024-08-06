package com.jpa.proyecto.domain.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jpa.proyecto.persistence.entities.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    Optional<Cuenta> findByUsername(String username);
}
