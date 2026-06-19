package org.example.vetalisbackend.inventory.domain.model.commands;

import java.time.LocalDateTime;

public record CreatePagoCommand(
        Long consultaId,
        Double monto,
        String metodoPago,
        LocalDateTime fechaPago,
        String estado
) {}
