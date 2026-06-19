package org.example.vetalisbackend.scheduling.application.commandservices;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;
import org.example.vetalisbackend.scheduling.domain.model.commands.CreateCitaCommand;

import java.util.Optional;

public interface CitaCommandService {
    Optional<Cita> handle(CreateCitaCommand command);
    Optional<Cita> update(Long id, CreateCitaCommand command);
    void deleteById(Long id);
}
