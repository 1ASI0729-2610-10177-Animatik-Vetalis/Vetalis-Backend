package org.example.vetalisbackend.iot.domain.repositories;

import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispensadorRepository extends JpaRepository<DispensadorIoT, Long> {
    Optional<DispensadorIoT> findByNumeroSerie(String numeroSerie);
}
