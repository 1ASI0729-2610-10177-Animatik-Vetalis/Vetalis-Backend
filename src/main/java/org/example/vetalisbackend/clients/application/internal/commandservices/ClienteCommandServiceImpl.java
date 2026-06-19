package org.example.vetalisbackend.clients.application.internal.commandservices;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;
import org.example.vetalisbackend.clients.domain.model.commands.CreateClienteCommand;
import org.example.vetalisbackend.clients.domain.model.commands.UpdateClienteCommand;
import org.example.vetalisbackend.clients.domain.repositories.ClienteRepository;
import org.example.vetalisbackend.clients.application.commandservices.ClienteCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteCommandServiceImpl implements ClienteCommandService {

    private final ClienteRepository clienteRepository;

    public ClienteCommandServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> handle(CreateClienteCommand command) {
        Cliente cliente = new Cliente(command.nombre(), command.dni(), command.telefono(),
                command.email(), command.direccion());
        return Optional.of(clienteRepository.save(cliente));
    }

    @Override
    public Optional<Cliente> handle(UpdateClienteCommand command) {
        return clienteRepository.findById(command.id()).map(cliente -> {
            cliente.setNombre(command.nombre());
            cliente.setDni(command.dni());
            cliente.setTelefono(command.telefono());
            cliente.setEmail(command.email());
            cliente.setDireccion(command.direccion());
            return clienteRepository.save(cliente);
        });
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
