package org.example.vetalisbackend.inventory.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.inventory.domain.model.commands.CreatePagoCommand;
import org.example.vetalisbackend.inventory.application.commandservices.PagoCommandService;
import org.example.vetalisbackend.inventory.application.queryservices.PagoQueryService;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.CreatePagoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.PagoResource;
import org.example.vetalisbackend.inventory.interfaces.rest.transform.PagoResourceFromEntityAssembler;
import org.example.vetalisbackend.inventory.domain.repositories.PagoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private final PagoCommandService pagoCommandService;
    private final PagoQueryService pagoQueryService;
    private final PagoRepository pagoRepository;

    public PagoController(PagoCommandService pagoCommandService,
                          PagoQueryService pagoQueryService,
                          PagoRepository pagoRepository) {
        this.pagoCommandService = pagoCommandService;
        this.pagoQueryService = pagoQueryService;
        this.pagoRepository = pagoRepository;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreatePagoResource resource) {
        CreatePagoCommand command = new CreatePagoCommand(resource.consultaId(), resource.monto(),
                resource.metodoPago(), resource.fechaPago(), resource.estado(),
                resource.medicamentoId(), resource.cantidad(), resource.descuento());
        try {
            return pagoCommandService.handle(command)
                    .map(p -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(PagoResourceFromEntityAssembler.fromDomainModel(p)))
                    .orElse(ResponseEntity.badRequest().build());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
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
                resource.metodoPago(), resource.fechaPago(), resource.estado(),
                resource.medicamentoId(), resource.cantidad(), resource.descuento());
        return pagoCommandService.update(id, command)
                .map(p -> ResponseEntity.ok(PagoResourceFromEntityAssembler.fromDomainModel(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    // US024 / TS008: Soft delete / void
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> anular(@PathVariable Long id,
                                       @RequestParam(defaultValue = "") String motivo) {
        var result = pagoCommandService.anular(id, motivo);
        if (result.isPresent()) return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }

    // US021: Cierre de caja diario
    @PostMapping("/cierre-caja")
    public ResponseEntity<Map<String, Object>> cierreCaja(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        LocalDate dia = fecha != null ? fecha : LocalDate.now();
        LocalDateTime inicio = dia.atStartOfDay();
        LocalDateTime fin = dia.atTime(LocalTime.MAX);

        Double total = pagoRepository.sumMontoByFechaPagoBetween(inicio, fin);
        List<Object[]> porMetodo = pagoRepository.sumByMetodoPago(inicio, fin);

        Map<String, Object> result = new HashMap<>();
        result.put("fecha", dia.toString());
        result.put("totalIngresos", total != null ? total : 0.0);

        Map<String, Double> detalleMetodos = new HashMap<>();
        for (Object[] row : porMetodo) {
            detalleMetodos.put((String) row[0], ((Number) row[1]).doubleValue());
        }
        result.put("porMetodoPago", detalleMetodos);
        return ResponseEntity.ok(result);
    }

    // US029: Reporte de ventas por rango de fechas
    @GetMapping("/reporte")
    public ResponseEntity<Map<String, Object>> reporte(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(LocalTime.MAX);

        List<PagoResource> pagos = pagoRepository.findByAnuladoFalseAndFechaPagoBetween(inicio, fin).stream()
                .map(PagoResourceFromEntityAssembler::fromDomainModel).toList();

        Double totalBruto = pagos.stream().mapToDouble(PagoResource::monto).sum();
        Double totalDescuento = pagos.stream()
                .mapToDouble(p -> p.descuento() != null ? p.descuento() : 0.0).sum();

        Map<String, Object> result = new HashMap<>();
        result.put("desde", desde.toString());
        result.put("hasta", hasta.toString());
        result.put("totalTransacciones", pagos.size());
        result.put("totalBruto", totalBruto);
        result.put("totalDescuentos", totalDescuento);
        result.put("totalNeto", totalBruto - totalDescuento);
        result.put("pagos", pagos);
        return ResponseEntity.ok(result);
    }

    // US030: Comisiones por veterinario
    @GetMapping("/comisiones")
    public ResponseEntity<Map<String, Object>> comisiones(
            @RequestParam Long veterinarioId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        LocalDateTime inicio = desde.atStartOfDay();
        LocalDateTime fin = hasta.atTime(LocalTime.MAX);

        List<PagoResource> pagos = pagoRepository
                .findByVeterinarioIdAndFechaPagoBetween(veterinarioId, inicio, fin).stream()
                .map(PagoResourceFromEntityAssembler::fromDomainModel).toList();

        double totalProduccion = pagos.stream().mapToDouble(PagoResource::monto).sum();

        Map<String, Object> result = new HashMap<>();
        result.put("veterinarioId", veterinarioId);
        result.put("desde", desde.toString());
        result.put("hasta", hasta.toString());
        result.put("totalProduccion", totalProduccion);
        result.put("pagos", pagos);
        return ResponseEntity.ok(result);
    }
}
