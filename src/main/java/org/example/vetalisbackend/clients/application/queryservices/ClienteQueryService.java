package org.example.vetalisbackend.clients.application.queryservices;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteQueryService {
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
}
