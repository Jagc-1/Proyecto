package com.jpa.proyecto.domain.services.region;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Region;

public interface RegionService {

    List<Region> findAll();
    Optional<Region> findById(Long id);
    Region save(Region region);
    Optional<Region> update(Long id, Region region);
    Optional<Region> delete(Long id);
}
