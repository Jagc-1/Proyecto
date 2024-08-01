package com.jpa.proyecto.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import com.jpa.proyecto.persistence.entities.Region;

public interface RegionRepository extends CrudRepository<Region, Long>{

}
