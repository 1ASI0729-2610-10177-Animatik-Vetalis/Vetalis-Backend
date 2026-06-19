package org.example.vetalisbackend.clients.domain.repositories;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}
