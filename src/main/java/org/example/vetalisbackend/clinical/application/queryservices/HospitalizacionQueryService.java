package org.example.vetalisbackend.clinical.application.queryservices;

import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;

import java.util.List;
import java.util.Optional;

public interface HospitalizacionQueryService {
    Optional<Hospitalizacion> findById(Long id);
    List<Hospitalizacion> findAll();
    List<Hospitalizacion> findByEstado(String estado);
    List<Hospitalizacion> findByMascotaId(Long mascotaId);
}
