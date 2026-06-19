package org.example.vetalisbackend.scheduling.application.queryservices;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CitaQueryService {
    Optional<Cita> findById(Long id);
    List<Cita> findAll();
    List<Cita> findByFechaAndVeterinarioId(LocalDate fecha, Long veterinarioId);
    List<Cita> findProximas();
}
