package org.example.vetalisbackend.clinical.interfaces.rest;

import org.example.vetalisbackend.clinical.application.queryservices.ConsultaQueryService;
import org.example.vetalisbackend.clinical.application.queryservices.HospitalizacionQueryService;
import org.example.vetalisbackend.clinical.application.queryservices.VacunaQueryService;
import org.example.vetalisbackend.clinical.domain.repositories.ConsultaRepository;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.ConsultaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.HospitalizacionResource;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.VacunaResource;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.ConsultaResourceFromEntityAssembler;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.HospitalizacionResourceFromEntityAssembler;
import org.example.vetalisbackend.clinical.interfaces.rest.transform.VacunaResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historial")
@CrossOrigin(origins = "*")
public class HistorialClinicoController {

    private final ConsultaQueryService consultaQueryService;
    private final VacunaQueryService vacunaQueryService;
    private final HospitalizacionQueryService hospitalizacionQueryService;
    private final ConsultaRepository consultaRepository;

    public HistorialClinicoController(ConsultaQueryService consultaQueryService,
                                      VacunaQueryService vacunaQueryService,
                                      HospitalizacionQueryService hospitalizacionQueryService,
                                      ConsultaRepository consultaRepository) {
        this.consultaQueryService = consultaQueryService;
        this.vacunaQueryService = vacunaQueryService;
        this.hospitalizacionQueryService = hospitalizacionQueryService;
        this.consultaRepository = consultaRepository;
    }

    @GetMapping("/{mascotaId}")
    public ResponseEntity<Map<String, Object>> getHistorial(
            @PathVariable Long mascotaId,
            @RequestParam(required = false) String tipo) {
        List<ConsultaResource> consultas;
        if (tipo != null && !tipo.isBlank()) {
            consultas = consultaRepository.findByMascotaIdAndTipo(mascotaId, tipo).stream()
                    .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
        } else {
            consultas = consultaRepository.findByMascotaIdOrderByFechaDesc(mascotaId).stream()
                    .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
        }
        List<VacunaResource> vacunas = vacunaQueryService.findByMascotaId(mascotaId).stream()
                .map(VacunaResourceFromEntityAssembler::fromDomainModel).toList();
        List<HospitalizacionResource> hospitalizaciones = hospitalizacionQueryService.findByMascotaId(mascotaId).stream()
                .map(HospitalizacionResourceFromEntityAssembler::fromDomainModel).toList();

        Map<String, Object> historial = Map.of(
                "mascotaId", mascotaId,
                "consultas", consultas,
                "vacunas", vacunas,
                "hospitalizaciones", hospitalizaciones
        );
        return ResponseEntity.ok(historial);
    }
}
