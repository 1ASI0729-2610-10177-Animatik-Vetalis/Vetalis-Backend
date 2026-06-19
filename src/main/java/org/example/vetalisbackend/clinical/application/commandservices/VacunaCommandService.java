package org.example.vetalisbackend.clinical.application.commandservices;

import org.example.vetalisbackend.clinical.domain.model.commands.CreateVacunaCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;

import java.util.Optional;

public interface VacunaCommandService {
    Optional<Vacuna> handle(CreateVacunaCommand command);
    Optional<Vacuna> update(Long id, CreateVacunaCommand command);
    void deleteById(Long id);
}
