package org.example.vetalisbackend.iot.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DispensarRequest(
        @NotNull @Positive
        Double cantidad
) {}
