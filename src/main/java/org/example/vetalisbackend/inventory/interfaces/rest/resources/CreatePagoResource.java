package org.example.vetalisbackend.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CreatePagoResource(
        Long consultaId,

        Long mascotaId,

        @NotNull @Positive
        Double monto,

        Double montoOriginal,

        String descripcion,

        @NotBlank @Size(max = 50)
        String metodoPago,

        String metodoPago2,

        Double monto2,

        @NotNull
        LocalDateTime fechaPago,

        @NotBlank @Size(max = 20)
        String estado,

        Long medicamentoId,

        @Positive
        Integer cantidad,

        Double descuento
) {}
