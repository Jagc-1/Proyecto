package com.jpa.proyecto.domain.services.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.EmpleadoRepository;
import com.jpa.proyecto.persistence.entities.Empleado;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository repository;

    @Transactional
    @Override
    public List<Empleado> findAll() {
        return (List<Empleado>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Empleado> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Empleado save(Empleado empleado) {
        return repository.save(empleado);
    }

    @Transactional
    @Override
    public Optional<Empleado> update(Long id, Empleado empleado) {
        Optional<Empleado> empleadoOpt = repository.findById(id);
        if (empleadoOpt.isPresent()) {
            Empleado empleadoItem = empleadoOpt.orElseThrow();
            empleadoItem.setNombres(empleado.getNombres());
            empleadoItem.setApellidos(empleado.getApellidos());
            empleadoItem.setOficina(empleado.getOficina());
            empleadoItem.setJefe(empleado.getJefe());
            empleadoItem.setPuesto(empleado.getPuesto());
            empleadoItem.setEmail(empleado.getEmail());
            empleadoItem.setContacto(empleado.getContacto());
            return Optional.of(repository.save(empleadoItem));
        }
        return empleadoOpt;
    }

    @Transactional
    @Override
    public Optional<Empleado> delete(Long id) {
        Optional<Empleado> empleadoOpt = repository.findById(id);
        empleadoOpt.ifPresent(empleadoItem -> {
            repository.delete(empleadoItem);
        });
        return empleadoOpt;
    }

    @Transactional
    @Override
    public List<Empleado> findEmployeesByOficina(Long oficinaId){
        return repository.findEmployeesByOficina(oficinaId);
    }
}
