package org.example.vetalisbackend.clinical.domain.model.commands;

public record CreateTratamientoCommand(
        Long consultaId,
        Long medicamentoId,
        Integer cantidad,
        String descripcion,
        String dosis,
        String frecuencia,
        String duracion
) {}
