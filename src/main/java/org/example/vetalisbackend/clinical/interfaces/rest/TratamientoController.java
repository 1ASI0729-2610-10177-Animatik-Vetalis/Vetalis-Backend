package org.example.vetalisbackend.clinical.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.clinical.application.commandservices.TratamientoCommandService;
import org.example.vetalisbackend.clinical.application.queryservices.TratamientoQueryService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateTratamientoCommand;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.CreateTratamientoResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.TratamientoResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.TratamientoResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tratamientos")
@CrossOrigin(origins = "*")
public class TratamientoController {

    private final TratamientoCommandService tratamientoCommandService;
    private final TratamientoQueryService tratamientoQueryService;

    public TratamientoController(TratamientoCommandService tratamientoCommandService,
                                  TratamientoQueryService tratamientoQueryService) {
        this.tratamientoCommandService = tratamientoCommandService;
        this.tratamientoQueryService = tratamientoQueryService;
    }

    @PostMapping
    public ResponseEntity<TratamientoResource> create(@RequestBody @Valid CreateTratamientoResource resource) {
        CreateTratamientoCommand command = new CreateTratamientoCommand(
                resource.consultaId(), resource.medicamentoId(), resource.descripcion(),
                resource.dosis(), resource.frecuencia(), resource.duracion());
        return tratamientoCommandService.handle(command)
                .map(t -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(TratamientoResourceFromEntityAssembler.fromDomainModel(t)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<TratamientoResource>> getAll(
            @RequestParam(required = false) Long consultaId) {
        List<TratamientoResource> resources;
        if (consultaId != null) {
            resources = tratamientoQueryService.findByConsultaId(consultaId).stream()
                    .map(TratamientoResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = tratamientoQueryService.findAll().stream()
                    .map(TratamientoResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamientoResource> getById(@PathVariable Long id) {
        return tratamientoQueryService.findById(id)
                .map(t -> ResponseEntity.ok(TratamientoResourceFromEntityAssembler.fromDomainModel(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamientoResource> update(@PathVariable Long id,
                                                       @RequestBody @Valid CreateTratamientoResource resource) {
        CreateTratamientoCommand command = new CreateTratamientoCommand(
                resource.consultaId(), resource.medicamentoId(), resource.descripcion(),
                resource.dosis(), resource.frecuencia(), resource.duracion());
        return tratamientoCommandService.update(id, command)
                .map(t -> ResponseEntity.ok(TratamientoResourceFromEntityAssembler.fromDomainModel(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tratamientoCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
