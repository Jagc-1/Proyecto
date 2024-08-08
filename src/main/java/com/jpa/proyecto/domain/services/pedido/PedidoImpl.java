package com.jpa.proyecto.domain.services.pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.PedidoRepository;
import com.jpa.proyecto.persistence.entities.Pedido;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoImpl implements PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Transactional
    @Override
    public List<Pedido> findAll() {
        return (List<Pedido>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Pedido> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    @Transactional
    @Override
    public Optional<Pedido> update(Long id, Pedido pedido) {
        Optional<Pedido> pedidoOpt = repository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedidoItem = pedidoOpt.orElseThrow();
            pedidoItem.setFechaPedido(pedido.getFechaPedido());
            pedidoItem.setFechaEsperada(pedido.getFechaEsperada());
            pedidoItem.setFechaEntregada(pedido.getFechaEntregada());
            pedidoItem.setEstado(pedido.getEstado());
            pedidoItem.setComentarios(pedido.getComentarios());
            pedidoItem.setCliente(pedido.getCliente());
            pedidoItem.setMetodo_pago(pedido.getMetodo_pago());
            return Optional.of(repository.save(pedidoItem));
        }
        return pedidoOpt;
    }

    @Transactional
    @Override
    public Optional<Pedido> delete(Long id) {
        Optional<Pedido> pedidoOpt = repository.findById(id);
        pedidoOpt.ifPresent(pedidoItem -> {
            repository.delete(pedidoItem);
        });
        return pedidoOpt;
    }

    @Transactional
    @Override
    public List<Pedido> findPedidoByEstado(Long estado){
        return repository.findPedidoByEstado(estado);
    }
}
