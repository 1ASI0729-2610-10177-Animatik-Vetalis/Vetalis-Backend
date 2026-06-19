package org.example.vetalisbackend.dashboard.interfaces.rest;

import org.example.vetalisbackend.clinical.domain.repositories.HospitalizacionRepository;
import org.example.vetalisbackend.clinical.domain.repositories.MascotaRepository;
import org.example.vetalisbackend.clinical.domain.repositories.VacunaRepository;
import org.example.vetalisbackend.dashboard.interfaces.rest.resources.ActividadRecienteResource;
import org.example.vetalisbackend.dashboard.interfaces.rest.resources.DashboardSummaryResource;
import org.example.vetalisbackend.dashboard.interfaces.rest.resources.ProximaCitaResource;
import org.example.vetalisbackend.scheduling.domain.repositories.CitaRepository;
import org.example.vetalisbackend.scheduling.application.queryservices.CitaQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final CitaRepository citaRepository;
    private final CitaQueryService citaQueryService;
    private final MascotaRepository mascotaRepository;
    private final HospitalizacionRepository hospitalizacionRepository;
    private final VacunaRepository vacunaRepository;

    public DashboardController(CitaRepository citaRepository,
                               CitaQueryService citaQueryService,
                               MascotaRepository mascotaRepository,
                               HospitalizacionRepository hospitalizacionRepository,
                               VacunaRepository vacunaRepository) {
        this.citaRepository = citaRepository;
        this.citaQueryService = citaQueryService;
        this.mascotaRepository = mascotaRepository;
        this.hospitalizacionRepository = hospitalizacionRepository;
        this.vacunaRepository = vacunaRepository;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryResource> getSummary() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        long citasHoy = citaRepository.countByFechaBetween(startOfDay, endOfDay);
        long pacientesActivos = mascotaRepository.countByEstado("ACTIVO");
        long hospitalizados = hospitalizacionRepository.countByFechaSalidaIsNull();
        long vacunasAplicadas = vacunaRepository.countByFechaAplicacionBetween(today, today);

        List<ProximaCitaResource> proximasCitas = citaQueryService.findProximas().stream()
                .limit(5)
                .map(c -> new ProximaCitaResource(
                        c.getId(), c.getMascotaId(), c.getVeterinarioId(),
                        c.getFecha(), c.getMotivo(), c.getTipo(),
                        c.getEstado() != null ? c.getEstado().name() : null))
                .toList();

        List<ActividadRecienteResource> actividadReciente = citaRepository
                .findByFechaBetween(startOfDay.minusDays(7), endOfDay).stream()
                .limit(10)
                .map(c -> new ActividadRecienteResource("CITA",
                        "Cita " + c.getTipo() + " - Mascota #" + c.getMascotaId(),
                        c.getFecha()))
                .toList();

        DashboardSummaryResource summary = new DashboardSummaryResource(
                citasHoy, pacientesActivos, hospitalizados, vacunasAplicadas,
                proximasCitas, actividadReciente);

        return ResponseEntity.ok(summary);
    }
}
