package com.jpa.proyecto.domain.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.jpa.proyecto.persistence.entities.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>{
    // Caso de Uso 11: Consultar Empleados por Oficina
@Query("SELECT e FROM Empleado e WHERE e.oficina.id = :oficinaId")
List<Empleado> findEmployeesByOficina(@Param("oficinaId") Long oficinaId);
}
