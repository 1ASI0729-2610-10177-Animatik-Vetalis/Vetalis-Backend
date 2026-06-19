package org.example.vetalisbackend.clinical.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.clinical.application.commandservices.HospitalizacionCommandService;
import org.example.vetalisbackend.clinical.application.queryservices.HospitalizacionQueryService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateHospitalizacionCommand;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.CreateHospitalizacionResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.HospitalizacionResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.HospitalizacionResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospitalizacion")
@CrossOrigin(origins = "*")
public class HospitalizacionController {

    private final HospitalizacionCommandService hospitalizacionCommandService;
    private final HospitalizacionQueryService hospitalizacionQueryService;

    public HospitalizacionController(HospitalizacionCommandService hospitalizacionCommandService,
                                     HospitalizacionQueryService hospitalizacionQueryService) {
        this.hospitalizacionCommandService = hospitalizacionCommandService;
        this.hospitalizacionQueryService = hospitalizacionQueryService;
    }

    @PostMapping
    public ResponseEntity<HospitalizacionResource> create(@RequestBody @Valid CreateHospitalizacionResource resource) {
        CreateHospitalizacionCommand command = new CreateHospitalizacionCommand(
                resource.mascotaId(), resource.fechaIngreso(), resource.fechaSalida(),
                resource.motivo(), resource.diagnostico(), resource.tratamiento(),
                resource.estado(), resource.veterinarioId());
        return hospitalizacionCommandService.handle(command)
                .map(h -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(HospitalizacionResourceFromEntityAssembler.fromDomainModel(h)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<HospitalizacionResource>> getAll(@RequestParam(required = false) String estado) {
        List<HospitalizacionResource> resources;
        if (estado != null) {
            resources = hospitalizacionQueryService.findByEstado(estado).stream()
                    .map(HospitalizacionResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = hospitalizacionQueryService.findAll().stream()
                    .map(HospitalizacionResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalizacionResource> getById(@PathVariable Long id) {
        return hospitalizacionQueryService.findById(id)
                .map(h -> ResponseEntity.ok(HospitalizacionResourceFromEntityAssembler.fromDomainModel(h)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalizacionResource> update(@PathVariable Long id,
                                                         @RequestBody @Valid CreateHospitalizacionResource resource) {
        CreateHospitalizacionCommand command = new CreateHospitalizacionCommand(
                resource.mascotaId(), resource.fechaIngreso(), resource.fechaSalida(),
                resource.motivo(), resource.diagnostico(), resource.tratamiento(),
                resource.estado(), resource.veterinarioId());
        return hospitalizacionCommandService.update(id, command)
                .map(h -> ResponseEntity.ok(HospitalizacionResourceFromEntityAssembler.fromDomainModel(h)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hospitalizacionCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
