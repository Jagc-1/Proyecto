package com.jpa.proyecto.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.jpa.proyecto.persistence.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    @Query("SELECT c " +
            "FROM Cliente c " +
            "JOIN c.ubicacion u " +
            "JOIN u.ciudad ci " +
            "WHERE ci.nombre = :ciudad")
    List<Cliente> findCustomersByCity(@Param("ciudad") String nombre);
}
