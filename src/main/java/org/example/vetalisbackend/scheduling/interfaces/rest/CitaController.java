package org.example.vetalisbackend.scheduling.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.scheduling.domain.model.commands.CreateCitaCommand;
import org.example.vetalisbackend.scheduling.domain.model.enums.EstadoCita;
import org.example.vetalisbackend.scheduling.application.commandservices.CitaCommandService;
import org.example.vetalisbackend.scheduling.application.queryservices.CitaQueryService;
import org.example.vetalisbackend.scheduling.interfaces.rest.resources.CitaResource;
import org.example.vetalisbackend.scheduling.interfaces.rest.resources.CreateCitaResource;
import org.example.vetalisbackend.scheduling.interfaces.rest.transform.CitaResourceFromEntityAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    private final CitaCommandService citaCommandService;
    private final CitaQueryService citaQueryService;

    public CitaController(CitaCommandService citaCommandService, CitaQueryService citaQueryService) {
        this.citaCommandService = citaCommandService;
        this.citaQueryService = citaQueryService;
    }

    @PostMapping
    public ResponseEntity<CitaResource> create(@RequestBody @Valid CreateCitaResource resource) {
        EstadoCita estado = resource.estado() != null ? EstadoCita.valueOf(resource.estado()) : EstadoCita.PENDIENTE;
        CreateCitaCommand command = new CreateCitaCommand(resource.mascotaId(), resource.veterinarioId(),
                resource.fecha(), resource.motivo(), resource.tipo(), estado);
        return citaCommandService.handle(command)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(CitaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping
    public ResponseEntity<List<CitaResource>> getAll(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Long veterinarioId) {
        List<CitaResource> resources;
        if (fecha != null) {
            resources = citaQueryService.findByFechaAndVeterinarioId(fecha, veterinarioId).stream()
                    .map(CitaResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = citaQueryService.findAll().stream()
                    .map(CitaResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResource> getById(@PathVariable Long id) {
        return citaQueryService.findById(id)
                .map(c -> ResponseEntity.ok(CitaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResource> update(@PathVariable Long id,
                                               @RequestBody @Valid CreateCitaResource resource) {
        EstadoCita estado = resource.estado() != null ? EstadoCita.valueOf(resource.estado()) : EstadoCita.PENDIENTE;
        CreateCitaCommand command = new CreateCitaCommand(resource.mascotaId(), resource.veterinarioId(),
                resource.fecha(), resource.motivo(), resource.tipo(), estado);
        return citaCommandService.update(id, command)
                .map(c -> ResponseEntity.ok(CitaResourceFromEntityAssembler.fromDomainModel(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        citaCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
