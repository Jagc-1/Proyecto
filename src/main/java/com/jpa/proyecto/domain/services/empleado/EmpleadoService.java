package com.jpa.proyecto.domain.services.empleado;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Empleado;

public interface EmpleadoService {

    List<Empleado> findAll();
    Optional<Empleado> findById(Long id);
    Empleado save(Empleado empleado);
    Optional<Empleado> update(Long id, Empleado empleado);
    Optional<Empleado> delete(Long id);
    List<Empleado> findEmployeesByOficina(Long oficinaId);
}