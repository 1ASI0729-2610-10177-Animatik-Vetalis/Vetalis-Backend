package org.example.vetalisbackend.clinical.interfaces.rest;

import org.example.vetalisbackend.clinical.application.queryservices.ConsultaQueryService;
import org.example.vetalisbackend.clinical.application.queryservices.HospitalizacionQueryService;
import org.example.vetalisbackend.clinical.application.queryservices.VacunaQueryService;
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

    public HistorialClinicoController(ConsultaQueryService consultaQueryService,
                                      VacunaQueryService vacunaQueryService,
                                      HospitalizacionQueryService hospitalizacionQueryService) {
        this.consultaQueryService = consultaQueryService;
        this.vacunaQueryService = vacunaQueryService;
        this.hospitalizacionQueryService = hospitalizacionQueryService;
    }

    @GetMapping("/{mascotaId}")
    public ResponseEntity<Map<String, Object>> getHistorial(@PathVariable Long mascotaId) {
        List<ConsultaResource> consultas = consultaQueryService.findByMascotaId(mascotaId).stream()
                .map(ConsultaResourceFromEntityAssembler::fromDomainModel).toList();
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
