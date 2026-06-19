package org.example.vetalisbackend.clinical.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.clinical.application.commandservices.ConsultaCommandService;
import org.example.vetalisbackend.clinical.application.queryservices.ConsultaQueryService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateConsultaCommand;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.ConsultaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.CreateConsultaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.ConsultaResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    private final ConsultaCommandService consultaCommandService;
    private final ConsultaQueryService consultaQueryService;

    public ConsultaController(ConsultaCommandService consultaCommandService,
                              ConsultaQueryService consultaQueryService) {
        this.consultaCommandService = consultaCommandService;
        this.consultaQueryService = consultaQueryService;
    }

    @PostMapping
    public ResponseEntity<ConsultaResource> create(@RequestBody @Valid CreateConsultaResource resource) {
        CreateConsultaCommand command = new CreateConsultaCommand(resource.mascotaId(), resource.veterinarioId(),
                resource.fecha(), resource.tipo(), resource.subjetivo(), resource.objetivo(),
                resource.evaluacion(), resource.plan(), resource.estado(), resource.diagnostico());
        return consultaCommandService.handle(command)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(ConsultaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResource>> getAll(@RequestParam(required = false) Long mascotaId) {
        List<ConsultaResource> resources;
        if (mascotaId != null) {
            resources = consultaQueryService.findByMascotaId(mascotaId).stream()
                    .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = consultaQueryService.findAll().stream()
                    .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaResource> getById(@PathVariable Long id) {
        return consultaQueryService.findById(id)
                .map(c -> ResponseEntity.ok(ConsultaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaResource> update(@PathVariable Long id,
                                                   @RequestBody @Valid CreateConsultaResource resource) {
        CreateConsultaCommand command = new CreateConsultaCommand(resource.mascotaId(), resource.veterinarioId(),
                resource.fecha(), resource.tipo(), resource.subjetivo(), resource.objetivo(),
                resource.evaluacion(), resource.plan(), resource.estado(), resource.diagnostico());
        return consultaCommandService.update(id, command)
                .map(c -> ResponseEntity.ok(ConsultaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
