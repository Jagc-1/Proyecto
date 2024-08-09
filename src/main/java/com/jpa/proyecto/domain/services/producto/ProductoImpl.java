package com.jpa.proyecto.domain.services.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.ProductoRepository;
import com.jpa.proyecto.persistence.entities.Producto;

@Service
public class ProductoImpl implements ProductoService {

    private final ProductoRepository repository;

    ProductoImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Producto> findAll() {
        return (List<Producto>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Producto> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Producto save(Producto producto) {
        return repository.save(producto);
    }

    @Transactional
    @Override
    public Optional<Producto> update(Long id, Producto producto) {
        Optional<Producto> productoOpt = repository.findById(id);
        if (productoOpt.isPresent()) {
            Producto productoItem = productoOpt.orElseThrow();

            productoItem.setNombre(producto.getNombre());
            productoItem.setGamaProducto(producto.getGamaProducto());
            productoItem.setDimension(producto.getDimension());
            productoItem.setProveedor(producto.getProveedor());
            productoItem.setDescripcion(producto.getDescripcion());
            productoItem.setStock(producto.getStock());
            productoItem.setPrecio(producto.getPrecio());
            return Optional.of(repository.save(productoItem));
        }
        return productoOpt;
    }

    @Transactional
    @Override
    public Optional<Producto> delete(Long id) {
        Optional<Producto> productoOpt = repository.findById(id);
        productoOpt.ifPresent(productoItem -> {
            repository.delete(productoItem);
        });
        return productoOpt;
    }

    @Transactional
    @Override
    public List<Producto> findProductsByGama(Long gamaProducto){
        return repository.findProductsByGama(gamaProducto);
    }

    @Transactional
    @Override
    public List<Producto> findProductsWithLowStock(int stockMinimo){
        return repository.findProductsWithLowStock(stockMinimo);
    }
}
