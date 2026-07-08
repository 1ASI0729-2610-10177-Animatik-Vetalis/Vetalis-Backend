package org.example.vetalisbackend.dashboard.interfaces.rest.resources;

import java.util.List;

public record DashboardSummaryResource(
        long citasHoy,
        long pacientesActivos,
        long hospitalizados,
        long vacunasAplicadas,
        double ingresosDia,
        List<ProximaCitaResource> proximasCitas,
        List<ActividadRecienteResource> actividadReciente
) {}
