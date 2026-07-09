package org.example.vetalisbackend.iot.domain.model.commands;

import org.example.vetalisbackend.iot.domain.model.enums.EstadoDispensador;

import java.time.LocalDateTime;

public record CreateDispensadorCommand(
        String numeroSerie,
        String modelo,
        EstadoDispensador estado,
        Double nivelAlimento,
        LocalDateTime ultimaConexion,
        Long mascotaId
) {}
