package org.example.vetalisbackend.clinical.application.commandservices;

import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateMascotaCommand;

import java.util.Optional;

public interface MascotaCommandService {
    Optional<Mascota> handle(CreateMascotaCommand command);
    Optional<Mascota> update(Long id, CreateMascotaCommand command);
    void deleteById(Long id);
}
