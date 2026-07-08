package org.example.vetalisbackend.clinical.application.queryservices;

import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;

import java.util.List;
import java.util.Optional;

public interface TratamientoQueryService {
    Optional<Tratamiento> findById(Long id);
    List<Tratamiento> findAll();
    List<Tratamiento> findByConsultaId(Long consultaId);
}
