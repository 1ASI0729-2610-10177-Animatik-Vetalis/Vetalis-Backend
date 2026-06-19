package org.example.vetalisbackend.inventory.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateMedicamentoResource(
        @NotBlank @Size(max = 100)
        String nombre,

        @Size(max = 500)
        String descripcion,

        @NotNull @PositiveOrZero
        Double precioUnitario,

        @NotNull @PositiveOrZero
        Integer stockActual,

        @NotNull @PositiveOrZero
        Integer puntoReorden
) {}
