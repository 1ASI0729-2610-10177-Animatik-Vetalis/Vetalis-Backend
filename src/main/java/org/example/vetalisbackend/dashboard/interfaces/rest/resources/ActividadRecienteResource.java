package org.example.vetalisbackend.dashboard.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ActividadRecienteResource(
        String tipo,
        String descripcion,
        LocalDateTime fecha
) {}
