package org.example.vetalisbackend.clinical.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateMascotaResource(
        @NotBlank @Size(max = 100)
        String nombre,

        @NotBlank @Size(max = 20)
        String sexo,

        @NotNull
        LocalDate fechaNacimiento,

        @NotNull @Positive
        Double peso,

        @NotBlank @Size(max = 20)
        String estado,

        String alergias,

        @NotNull
        Long clienteId,

        @NotNull
        Long razaId
) {}
