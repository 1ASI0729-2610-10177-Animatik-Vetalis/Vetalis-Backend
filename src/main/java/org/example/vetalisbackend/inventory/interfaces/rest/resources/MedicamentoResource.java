package org.example.vetalisbackend.inventory.interfaces.rest.resources;

public record MedicamentoResource(
        Long id,
        String nombre,
        String descripcion,
        Double precioUnitario,
        Integer stockActual,
        Integer puntoReorden
) {}
