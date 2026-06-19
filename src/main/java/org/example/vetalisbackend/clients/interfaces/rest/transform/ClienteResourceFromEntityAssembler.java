package org.example.vetalisbackend.clients.interfaces.rest.transform;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;
import org.example.vetalisbackend.clients.interfaces.rest.resources.ClienteResource;

public class ClienteResourceFromEntityAssembler {
    public static ClienteResource fromDomainModel(Cliente cliente) {
        return new ClienteResource(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDni(),
                cliente.getTelefono(),
                cliente.getEmail(),
                cliente.getDireccion()
        );
    }
}
