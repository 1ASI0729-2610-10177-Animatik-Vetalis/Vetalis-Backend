package org.example.vetalisbackend.inventory.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.inventory.domain.model.commands.CreateMedicamentoCommand;
import org.example.vetalisbackend.inventory.application.commandservices.MedicamentoCommandService;
import org.example.vetalisbackend.inventory.application.queryservices.MedicamentoQueryService;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.CreateMedicamentoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.MedicamentoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.transform.MedicamentoResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "*")
public class MedicamentoController {

    private final MedicamentoCommandService medicamentoCommandService;
    private final MedicamentoQueryService medicamentoQueryService;

    public MedicamentoController(MedicamentoCommandService medicamentoCommandService,
                                 MedicamentoQueryService medicamentoQueryService) {
        this.medicamentoCommandService = medicamentoCommandService;
        this.medicamentoQueryService = medicamentoQueryService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoResource> create(@RequestBody @Valid CreateMedicamentoResource resource) {
        CreateMedicamentoCommand command = new CreateMedicamentoCommand(resource.nombre(), resource.descripcion(),
                resource.precioUnitario(), resource.stockActual(), resource.puntoReorden());
        return medicamentoCommandService.handle(command)
                .map(m -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(MedicamentoResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResource>> getAll() {
        List<MedicamentoResource> resources = medicamentoQueryService.findAll().stream()
                .map(MedicamentoResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<MedicamentoResource>> getLowStock() {
        List<MedicamentoResource> resources = medicamentoQueryService.findLowStock().stream()
                .map(MedicamentoResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResource> getById(@PathVariable Long id) {
        return medicamentoQueryService.findById(id)
                .map(m -> ResponseEntity.ok(MedicamentoResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResource> update(@PathVariable Long id,
                                                      @RequestBody @Valid CreateMedicamentoResource resource) {
        CreateMedicamentoCommand command = new CreateMedicamentoCommand(resource.nombre(), resource.descripcion(),
                resource.precioUnitario(), resource.stockActual(), resource.puntoReorden());
        return medicamentoCommandService.update(id, command)
                .map(m -> ResponseEntity.ok(MedicamentoResourceFromEntityAssembler.fromDomainModel(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicamentoCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
