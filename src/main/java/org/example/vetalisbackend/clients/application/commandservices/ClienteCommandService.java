package org.example.vetalisbackend.clients.application.commandservices;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;
import org.example.vetalisbackend.clients.domain.model.commands.CreateClienteCommand;
import org.example.vetalisbackend.clients.domain.model.commands.UpdateClienteCommand;

import java.util.Optional;

public interface ClienteCommandService {
    Optional<Cliente> handle(CreateClienteCommand command);
    Optional<Cliente> handle(UpdateClienteCommand command);
    void deleteById(Long id);
}
