package com.jpa.proyecto.domain.services.credito;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.CreditoRepository;
import com.jpa.proyecto.persistence.entities.Credito;


@Service
public class CreditoImpl implements CreditoService {

    @Autowired
    private CreditoRepository repository;

    @Transactional
    @Override
    public List<Credito> findAll() {
        return (List<Credito>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Credito> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Credito save(Credito credito) {
        return repository.save(credito);
    }

    @Transactional
    @Override
    public Optional<Credito> update(Long id, Credito credito) {
        Optional<Credito> creditoOpt = repository.findById(id);
        if (creditoOpt.isPresent()) {
            Credito creditoItem = creditoOpt.orElseThrow();
            creditoItem.setLimite(credito.getLimite());
            creditoItem.setEstado(credito.getEstado());
            return Optional.of(repository.save(creditoItem));
        }
        return creditoOpt;
    }

    @Transactional
    @Override
    public Optional<Credito> delete(Long id) {
        Optional<Credito> creditoOpt = repository.findById(id);
        creditoOpt.ifPresent(creditoItem -> {
            repository.delete(creditoItem);
        });
        return creditoOpt;
    }
}