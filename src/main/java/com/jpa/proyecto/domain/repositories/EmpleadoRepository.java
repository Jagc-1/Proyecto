package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{

}
