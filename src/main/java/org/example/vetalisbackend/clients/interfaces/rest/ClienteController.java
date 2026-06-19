package org.example.vetalisbackend.clients.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.clients.domain.model.commands.CreateClienteCommand;
import org.example.vetalisbackend.clients.domain.model.commands.UpdateClienteCommand;
import org.example.vetalisbackend.clients.application.commandservices.ClienteCommandService;
import org.example.vetalisbackend.clients.application.queryservices.ClienteQueryService;
import org.example.vetalisbackend.clients.interfaces.rest.resources.ClienteResource;
import org.example.vetalisbackend.clients.interfaces.rest.resources.CreateClienteResource;
import org.example.vetalisbackend.clients.interfaces.rest.transform.ClienteResourceFromEntityAssembler;
import org.example.vetalisbackend.clients.interfaces.rest.transform.CreateClienteCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteCommandService clienteCommandService;
    private final ClienteQueryService clienteQueryService;

    public ClienteController(ClienteCommandService clienteCommandService,
                             ClienteQueryService clienteQueryService) {
        this.clienteCommandService = clienteCommandService;
        this.clienteQueryService = clienteQueryService;
    }

    @PostMapping
    public ResponseEntity<ClienteResource> create(@RequestBody @Valid CreateClienteResource resource) {
        CreateClienteCommand command = CreateClienteCommandFromResourceAssembler.toCommand(resource);
        return clienteCommandService.handle(command)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(ClienteResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ClienteResource>> getAll() {
        List<ClienteResource> resources = clienteQueryService.findAll().stream()
                .map(ClienteResourceFromEntityAssembler::fromDomainModel)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> getById(@PathVariable Long id) {
        return clienteQueryService.findById(id)
                .map(c -> ResponseEntity.ok(ClienteResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> update(@PathVariable Long id,
                                                  @RequestBody @Valid CreateClienteResource resource) {
        UpdateClienteCommand command = new UpdateClienteCommand(id, resource.nombre(), resource.dni(),
                resource.telefono(), resource.email(), resource.direccion());
        return clienteCommandService.handle(command)
                .map(c -> ResponseEntity.ok(ClienteResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
