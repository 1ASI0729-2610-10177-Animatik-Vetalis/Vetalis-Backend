package org.example.vetalisbackend.clinical.application.commandservices;

import org.example.vetalisbackend.clinical.domain.model.commands.CreateTratamientoCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;

import java.util.Optional;

public interface TratamientoCommandService {
    Optional<Tratamiento> handle(CreateTratamientoCommand command);
    Optional<Tratamiento> update(Long id, CreateTratamientoCommand command);
    void deleteById(Long id);
}
