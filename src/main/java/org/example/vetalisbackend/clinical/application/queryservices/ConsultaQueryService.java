package org.example.vetalisbackend.clinical.application.queryservices;

import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaQueryService {
    Optional<Consulta> findById(Long id);
    List<Consulta> findAll();
    List<Consulta> findByMascotaId(Long mascotaId);
}
