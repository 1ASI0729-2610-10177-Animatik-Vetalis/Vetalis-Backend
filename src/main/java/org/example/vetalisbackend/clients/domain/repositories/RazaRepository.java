package org.example.vetalisbackend.clients.domain.repositories;

import org.example.vetalisbackend.clients.domain.model.entities.Raza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RazaRepository extends JpaRepository<Raza, Long> {
    List<Raza> findByEspecieId(Long especieId);
}
