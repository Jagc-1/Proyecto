package com.jpa.proyecto.domain.services.detallePedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.DetallePedidoRepository;
import com.jpa.proyecto.persistence.entities.DetallePedido;

@Service
public class DetallePedidoImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repository;

    @Transactional
    @Override
    public List<DetallePedido> findAll() {
        return (List<DetallePedido>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<DetallePedido> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public DetallePedido save(DetallePedido detallePedido) {
        return repository.save(detallePedido);
    }

    @Transactional
    @Override
    public Optional<DetallePedido> update(Long id, DetallePedido detallePedido) {
        Optional<DetallePedido> detalleOpt = repository.findById(id);
        if (detalleOpt.isPresent()) {
            DetallePedido detalleItem = detalleOpt.orElseThrow();
            detalleItem.setProducto(detallePedido.getProducto());
            detalleItem.setCantidad(detallePedido.getCantidad());
            detalleItem.setNumero_linea(detalleItem.getNumero_linea());
            detalleItem.setPedido(detallePedido.getPedido());
            detalleItem.setPrecio_unidad(detallePedido.getPrecio_unidad());
            return Optional.of(repository.save(detalleItem));
        }
        return detalleOpt;
    }

    @Transactional
    @Override
    public Optional<DetallePedido> delete(Long id) {
        Optional<DetallePedido> detalleOpt = repository.findById(id);
        detalleOpt.ifPresent(detalleItem -> {
            repository.delete(detalleItem);
        });
        return detalleOpt;
    }
}
