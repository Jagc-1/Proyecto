package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Stock;

public interface StockRepository extends CrudRepository<Stock, Long>{

}
