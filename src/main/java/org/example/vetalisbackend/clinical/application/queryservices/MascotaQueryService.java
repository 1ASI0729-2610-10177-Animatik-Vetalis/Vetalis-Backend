package org.example.vetalisbackend.clinical.application.queryservices;

import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaQueryService {
    Optional<Mascota> findById(Long id);
    List<Mascota> findAll();
    List<Mascota> findByClienteId(Long clienteId);
}
