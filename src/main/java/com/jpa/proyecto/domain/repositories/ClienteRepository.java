package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
