package org.example.vetalisbackend.clients.interfaces.rest.transform;

import org.example.vetalisbackend.clients.domain.model.commands.CreateClienteCommand;
import org.example.vetalisbackend.clients.interfaces.rest.resources.CreateClienteResource;

public class CreateClienteCommandFromResourceAssembler {
    public static CreateClienteCommand toCommand(CreateClienteResource resource) {
        return new CreateClienteCommand(
                resource.nombre(),
                resource.dni(),
                resource.telefono(),
                resource.email(),
                resource.direccion()
        );
    }
}
