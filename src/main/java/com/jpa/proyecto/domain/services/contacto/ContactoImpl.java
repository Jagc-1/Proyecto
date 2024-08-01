package com.jpa.proyecto.domain.services.contacto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.proyecto.domain.repositories.ContactoRepository;
import com.jpa.proyecto.persistence.entities.Contacto;



@Service
public class ContactoImpl implements ContactoService {

    @Autowired
    private ContactoRepository repository;

    @Transactional
    @Override
    public List<Contacto> findAll() {
        return (List<Contacto>) repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Contacto> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Contacto save(Contacto contacto) {
        return repository.save(contacto);
    }

    @Transactional
    @Override
    public Optional<Contacto> update(Long id, Contacto contacto) {
        Optional<Contacto> contactoOpt = repository.findById(id);
        if (contactoOpt.isPresent()) {
            Contacto contactoItem = contactoOpt.orElseThrow();
            contactoItem.setFax(contacto.getFax());
            contactoItem.setTelefono(contacto.getTelefono());
            contactoItem.setEmail(contacto.getEmail());
            contactoItem.setExtension(contacto.getExtension());
            return Optional.of(repository.save(contactoItem));
        }
        return contactoOpt;
    }

    @Transactional
    @Override
    public Optional<Contacto> delete(Long id) {
        Optional<Contacto> contactoOpt = repository.findById(id);
        contactoOpt.ifPresent(contactoItem -> {
            repository.delete(contactoItem);
        });
        return contactoOpt;
    }
}