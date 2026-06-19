package org.example.vetalisbackend.iot.application.commandservices;

import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;
import org.example.vetalisbackend.iot.domain.model.commands.CreateDispensadorCommand;

import java.util.Optional;

public interface DispensadorCommandService {
    Optional<DispensadorIoT> handle(CreateDispensadorCommand command);
    Optional<DispensadorIoT> update(Long id, CreateDispensadorCommand command);
    Optional<DispensadorIoT> dispensar(Long id, Double cantidad);
    void deleteById(Long id);
}
