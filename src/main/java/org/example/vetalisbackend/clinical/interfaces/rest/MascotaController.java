package org.example.vetalisbackend.clinical.interfaces.rest;

import jakarta.validation.Valid;
import org.example.vetalisbackend.clinical.application.commandservices.MascotaCommandService;
import org.example.vetalisbackend.clinical.application.queryservices.ConsultaQueryService;
import org.example.vetalisbackend.clinical.application.queryservices.MascotaQueryService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateMascotaCommand;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.ConsultaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.CreateMascotaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.MascotaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.ConsultaResourceFromEntityAssembler;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.MascotaResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    private final MascotaCommandService mascotaCommandService;
    private final MascotaQueryService mascotaQueryService;
    private final ConsultaQueryService consultaQueryService;

    public MascotaController(MascotaCommandService mascotaCommandService,
                             MascotaQueryService mascotaQueryService,
                             ConsultaQueryService consultaQueryService) {
        this.mascotaCommandService = mascotaCommandService;
        this.mascotaQueryService = mascotaQueryService;
        this.consultaQueryService = consultaQueryService;
    }

    @PostMapping
    public ResponseEntity<MascotaResource> create(@RequestBody @Valid CreateMascotaResource resource) {
        CreateMascotaCommand command = new CreateMascotaCommand(resource.nombre(), resource.sexo(),
                resource.fechaNacimiento(), resource.peso(), resource.estado(),
                resource.clienteId(), resource.razaId());
        return mascotaCommandService.handle(command)
                .map(m -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(MascotaResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<MascotaResource>> getAll(@RequestParam(required = false) Long clienteId) {
        List<MascotaResource> resources;
        if (clienteId != null) {
            resources = mascotaQueryService.findByClienteId(clienteId).stream()
                    .map(MascotaResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            resources = mascotaQueryService.findAll().stream()
                    .map(MascotaResourceFromEntityAssembler::fromDomainModel).toList();
        }
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResource> getById(@PathVariable Long id) {
        return mascotaQueryService.findById(id)
                .map(m -> ResponseEntity.ok(MascotaResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<ConsultaResource>> getHistorial(@PathVariable Long id) {
        List<ConsultaResource> consultas = consultaQueryService.findByMascotaId(id).stream()
                .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(consultas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResource> update(@PathVariable Long id,
                                                  @RequestBody @Valid CreateMascotaResource resource) {
        CreateMascotaCommand command = new CreateMascotaCommand(resource.nombre(), resource.sexo(),
                resource.fechaNacimiento(), resource.peso(), resource.estado(),
                resource.clienteId(), resource.razaId());
        return mascotaCommandService.update(id, command)
                .map(m -> ResponseEntity.ok(MascotaResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mascotaCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
