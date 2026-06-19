package org.example.vetalisbackend.inventory.domain.model.commands;

public record CreateMedicamentoCommand(
        String nombre,
        String descripcion,
        Double precioUnitario,
        Integer stockActual,
        Integer puntoReorden
) {}
