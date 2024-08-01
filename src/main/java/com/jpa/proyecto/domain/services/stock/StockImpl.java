package com.jpa.proyecto.domain.services.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.StockRepository;
import com.jpa.proyecto.persistence.entities.Stock;

import java.util.List;
import java.util.Optional;

@Service
public class StockImpl implements StockService {

    @Autowired
    private StockRepository repository;

    @Transactional
    @Override
    public List<Stock> findAll() {
        return (List<Stock>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Stock> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Stock save(Stock stock) {
        return repository.save(stock);
    }

    @Transactional
    @Override
    public Optional<Stock> update(Long id, Stock stock) {
        Optional<Stock> stockOpt = repository.findById(id);
        if (stockOpt.isPresent()) {
            Stock stockItem = stockOpt.orElseThrow();
            stockItem.setStock_actual(stock.getStock_actual());
            stockItem.setStock_maximo(stock.getStock_maximo());
            stockItem.setStock_minimo(stock.getStock_minimo());
            return Optional.of(repository.save(stockItem));
        }
        return stockOpt;
    }

    @Transactional
    @Override
    public Optional<Stock> delete(Long id) {
        Optional<Stock> stockOpt = repository.findById(id);
        stockOpt.ifPresent(stockItem -> {
            repository.delete(stockItem);
        });
        return stockOpt;
    }
}

