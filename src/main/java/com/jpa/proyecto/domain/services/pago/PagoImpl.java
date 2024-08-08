package com.jpa.proyecto.domain.services.pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.PagoRepository;
import com.jpa.proyecto.persistence.entities.Pago;

import java.util.List;
import java.util.Optional;

@Service
public class PagoImpl implements PagoService {

    @Autowired
    private PagoRepository repository;

    @Transactional
    @Override
    public List<Pago> findAll() {
        return (List<Pago>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Pago> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Pago save(Pago pago) {
        return repository.save(pago);
    }

    @Transactional
    @Override
    public Optional<Pago> update(Long id, Pago pago) {
        Optional<Pago> pagoOpt = repository.findById(id);
        if (pagoOpt.isPresent()) {
            Pago pagoItem = pagoOpt.orElseThrow();
            pagoItem.setFecha_pago(pago.getFecha_pago());
            pagoItem.setMonto(pago.getMonto());
            pagoItem.setPedido(pago.getPedido());
            return Optional.of(repository.save(pagoItem));
        }
        return pagoOpt;
    }

    @Transactional
    @Override
    public Optional<Pago> delete(Long id) {
        Optional<Pago> pagoOpt = repository.findById(id);
        pagoOpt.ifPresent(pagoItem -> {
            repository.delete(pagoItem);
        });
        return pagoOpt;
    }

    @Transactional
    @Override
    public List<Pago> findPagosByCliente(Long cliente){
        return repository.findPagosByCliente(cliente);
    }
}
