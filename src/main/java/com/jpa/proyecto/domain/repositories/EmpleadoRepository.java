package com.jpa.proyecto.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.proyecto.persistence.entities.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {
    // Caso de Uso 11: Consultar Empleados por Oficina
    @Query("SELECT e FROM Empleado e WHERE e.oficina.id = :oficinaId")
    List<Empleado> findEmployeesByOficina(@Param("oficinaId") Long oficinaId);

    @Query("SELECT DISTINCT e FROM Empleado e " +
            "JOIN Cliente c ON c.empleado.id = e.id " +
            "JOIN Pedido p ON p.cliente.id = c.id")
    List<Empleado> findEmployeesWithAssignedOrders();

}
