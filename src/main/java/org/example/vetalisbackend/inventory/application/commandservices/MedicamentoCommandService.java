package org.example.vetalisbackend.inventory.application.commandservices;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.example.vetalisbackend.inventory.domain.model.commands.CreateMedicamentoCommand;

import java.util.Optional;

public interface MedicamentoCommandService {
    Optional<Medicamento> handle(CreateMedicamentoCommand command);
    Optional<Medicamento> update(Long id, CreateMedicamentoCommand command);
    void deleteById(Long id);
}
