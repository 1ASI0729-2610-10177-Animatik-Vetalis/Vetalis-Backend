package org.example.vetalisbackend.inventory.application.queryservices;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;

import java.util.List;
import java.util.Optional;

public interface PagoQueryService {
    Optional<Pago> findById(Long id);
    List<Pago> findAll();
    List<Pago> findByConsultaId(Long consultaId);
}
