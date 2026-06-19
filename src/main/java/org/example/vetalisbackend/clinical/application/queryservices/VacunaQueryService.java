package org.example.vetalisbackend.clinical.application.queryservices;

import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;

import java.util.List;
import java.util.Optional;

public interface VacunaQueryService {
    Optional<Vacuna> findById(Long id);
    List<Vacuna> findAll();
    List<Vacuna> findByMascotaId(Long mascotaId);
    List<Vacuna> findAlerts();
}
