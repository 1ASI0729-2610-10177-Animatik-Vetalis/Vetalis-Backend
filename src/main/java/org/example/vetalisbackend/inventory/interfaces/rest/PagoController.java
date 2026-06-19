package org.example.vetalisbackend.inventory.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.inventory.domain.model.commands.CreatePagoCommand;
import org.example.vetalisbackend.inventory.application.commandservices.PagoCommandService;
import org.example.vetalisbackend.inventory.application.queryservices.PagoQueryService;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.CreatePagoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.PagoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.transform.PagoResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private final PagoCommandService pagoCommandService;
    private final PagoQueryService pagoQueryService;

    public PagoController(PagoCommandService pagoCommandService, PagoQueryService pagoQueryService) {
        this.pagoCommandService = pagoCommandService;
        this.pagoQueryService = pagoQueryService;
    }

    @PostMapping
    public ResponseEntity<PagoResource> create(@RequestBody @Valid CreatePagoResource resource) {
        CreatePagoCommand command = new CreatePagoCommand(resource.consultaId(), resource.monto(),
                resource.metodoPago(), resource.fechaPago(), resource.estado());
        return pagoCommandService.handle(command)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(PagoResourceFromEntityAssembler.fromDomainModel(p)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<PagoResource>> getAll() {
        List<PagoResource> resources = pagoQueryService.findAll().stream()
                .map(PagoResourceFromEntityAssembler::fromDomainModel).toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoResource> getById(@PathVariable Long id) {
        return pagoQueryService.findById(id)
                .map(p -> ResponseEntity.ok(PagoResourceFromEntityAssembler.fromDomainModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResource> update(@PathVariable Long id, @RequestBody @Valid CreatePagoResource resource) {
        CreatePagoCommand command = new CreatePagoCommand(resource.consultaId(), resource.monto(),
                resource.metodoPago(), resource.fechaPago(), resource.estado());
        return pagoCommandService.update(id, command)
                .map(p -> ResponseEntity.ok(PagoResourceFromEntityAssembler.fromDomainModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pagoCommandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
