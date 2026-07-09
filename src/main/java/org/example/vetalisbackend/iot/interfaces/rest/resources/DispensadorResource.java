package org.example.vetalisbackend.iot.interfaces.rest.resources;

import java.time.LocalDateTime;

public record DispensadorResource(
        Long id,
        String numeroSerie,
        String modelo,
        String estado,
        Double nivelAlimento,
        LocalDateTime ultimaConexion,
        Long mascotaId
) {}
