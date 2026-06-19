package org.example.vetalisbackend.clients.domain.repositories;

import org.example.vetalisbackend.clients.domain.model.entities.Especie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecieRepository extends JpaRepository<Especie, Long> {}
