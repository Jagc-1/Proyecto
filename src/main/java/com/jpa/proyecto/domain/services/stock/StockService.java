package com.jpa.proyecto.domain.services.stock;

import java.util.List;
import java.util.Optional;

import com.jpa.proyecto.persistence.entities.Stock;

public interface StockService {

    List<Stock> findAll();
    Optional<Stock> findById(Long id);
    Stock save(Stock stock);
    Optional<Stock> update(Long id, Stock stock);
    Optional<Stock> delete(Long id);
}
