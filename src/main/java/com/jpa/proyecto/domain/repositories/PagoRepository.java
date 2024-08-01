package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Pago;

public interface PagoRepository extends CrudRepository<Pago, Long>{

}
