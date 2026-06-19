package org.example.vetalisbackend.inventory.application.queryservices;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoQueryService {
    Optional<Medicamento> findById(Long id);
    List<Medicamento> findAll();
    List<Medicamento> findLowStock();
}
