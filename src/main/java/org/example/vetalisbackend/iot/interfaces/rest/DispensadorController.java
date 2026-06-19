package org.example.vetalisbackend.iot.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.iot.application.commandservices.DispensadorCommandService;
import org.example.vetalisbackend.iot.application.queryservices.DispensadorQueryService;
import org.example.vetalisbackend.iot.domain.model.commands.CreateDispensadorCommand;
import org.example.vetalisbackend.iot.domain.model.enums.EstadoDispensador;
import org.example.vetalisbackend.iot.interfaces.rest.resources.CreateDispensadorResource;
import org.example.vetalisbackend.iot.interfaces.rest.resources.DispensadorResource;
import org.example.vetalisbackend.iot.interfaces.rest.resources.DispensarRequest;
import org.example.vetalisbackend.iot.interfaces.rest.transform.DispensadorResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispensadores")
@CrossOrigin(origins = "*")
public class DispensadorController {

    private final DispensadorCommandService dispensadorCommandService;
    private final DispensadorQueryService dispensadorQueryService;

    public DispensadorController(DispensadorCommandService dispensadorCommandService,
                                 DispensadorQueryService dispensadorQueryService) {
        this.dispensadorCommandService = dispensadorCommandService;
        this.dispensadorQueryService = dispensadorQueryService;
    }

    @PostMapping
    public ResponseEntity<DispensadorResource> create(@RequestBody @Valid CreateDispensadorResource resource) {
        EstadoDispensador estado = resource.estado() != null
                ? EstadoDispensador.valueOf(resource.estado()) : EstadoDispensador.INACTIVO;
        CreateDispensadorCommand command = new CreateDispensadorCommand(resource.numeroSerie(),
                resource.modelo(), estado, resource.nivelAlimento(), resource.ultimaConexion());
        return dispensadorCommandService.handle(command)
                .map(d -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(DispensadorResourceFromEntityAssembler.fromDomainModel(d)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<DispensadorResource>> getAll() {
        List<DispensadorResource> resources = dispensadorQueryService.findAll().stream()
                .map(DispensadorResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispensadorResource> getById(@PathVariable Long id) {
        return dispensadorQueryService.findById(id)
                .map(d -> ResponseEntity.ok(DispensadorResourceFromEntityAssembler.fromDomainModel(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispensadorResource> update(@PathVariable Long id,
                                                      @RequestBody @Valid CreateDispensadorResource resource) {
        EstadoDispensador estado = resource.estado() != null
                ? EstadoDispensador.valueOf(resource.estado()) : EstadoDispensador.INACTIVO;
        CreateDispensadorCommand command = new CreateDispensadorCommand(resource.numeroSerie(),
                resource.modelo(), estado, resource.nivelAlimento(), resource.ultimaConexion());
        return dispensadorCommandService.update(id, command)
                .map(d -> ResponseEntity.ok(DispensadorResourceFromEntityAssembler.fromDomainModel(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/dispensar")
    public ResponseEntity<DispensadorResource> dispensar(@PathVariable Long id,
                                                         @RequestBody @Valid DispensarRequest request) {
        return dispensadorCommandService.dispensar(id, request.cantidad())
                .map(d -> ResponseEntity.ok(DispensadorResourceFromEntityAssembler.fromDomainModel(d)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dispensadorCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
