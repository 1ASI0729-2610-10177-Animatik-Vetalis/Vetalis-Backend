package org.example.vetalisbackend.inventory.application.commandservices;

import org.example.vetalisbackend.inventory.domain.model.commands.CreatePagoCommand;
import org.example.vetalisbackend.inventory.domain.model.entities.Pago;

import java.util.Optional;

public interface PagoCommandService {
    Optional<Pago> handle(CreatePagoCommand command);
    Optional<Pago> update(Long id, CreatePagoCommand command);
    Optional<Pago> anular(Long id, String motivo);
    void deleteById(Long id);
}
