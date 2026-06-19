package org.example.vetalisbackend.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreatePagoResource(
        @NotNull
        Long consultaId,

        @NotNull @Positive
        Double monto,

        @NotBlank @Size(max = 50)
        String metodoPago,

        @NotNull
        LocalDateTime fechaPago,

        @NotBlank @Size(max = 20)
        String estado
) {}
