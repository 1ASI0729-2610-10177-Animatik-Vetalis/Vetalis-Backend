package org.example.vetalisbackend.clinical.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.clinical.application.commandservices.VacunaCommandService;
import org.example.vetalisbackend.clinical.application.queryservices.VacunaQueryService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateVacunaCommand;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.CreateVacunaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.VacunaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.VacunaResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacunas")
@CrossOrigin(origins = "*")
public class VacunaController {

    private final VacunaCommandService vacunaCommandService;
    private final VacunaQueryService vacunaQueryService;

    public VacunaController(VacunaCommandService vacunaCommandService,
                            VacunaQueryService vacunaQueryService) {
        this.vacunaCommandService = vacunaCommandService;
        this.vacunaQueryService = vacunaQueryService;
    }

    @PostMapping
    public ResponseEntity<VacunaResource> create(@RequestBody @Valid CreateVacunaResource resource) {
        CreateVacunaCommand command = new CreateVacunaCommand(resource.mascotaId(), resource.tipoVacunaId(),
                resource.nombreVacuna(), resource.lote(), resource.fechaAplicacion(),
                resource.proximaDosis(), resource.estado(), resource.veterinarioId());
        return vacunaCommandService.handle(command)
                .map(v -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(VacunaResourceFromEntityAssembler.fromDomainModel(v)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<VacunaResource>> getAll(@RequestParam(required = false) Long mascotaId) {
        List<VacunaResource> resources;
        if (mascotaId != null) {
            resources = vacunaQueryService.findByMascotaId(mascotaId).stream()
                    .map(VacunaResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = vacunaQueryService.findAll().stream()
                    .map(VacunaResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<VacunaResource>> getAlerts() {
        List<VacunaResource> alerts = vacunaQueryService.findAlerts().stream()
                .map(VacunaResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacunaResource> getById(@PathVariable Long id) {
        return vacunaQueryService.findById(id)
                .map(v -> ResponseEntity.ok(VacunaResourceFromEntityAssembler.fromDomainModel(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacunaResource> update(@PathVariable Long id,
                                                 @RequestBody @Valid CreateVacunaResource resource) {
        CreateVacunaCommand command = new CreateVacunaCommand(resource.mascotaId(), resource.tipoVacunaId(),
                resource.nombreVacuna(), resource.lote(), resource.fechaAplicacion(),
                resource.proximaDosis(), resource.estado(), resource.veterinarioId());
        return vacunaCommandService.update(id, command)
                .map(v -> ResponseEntity.ok(VacunaResourceFromEntityAssembler.fromDomainModel(v)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacunaCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
