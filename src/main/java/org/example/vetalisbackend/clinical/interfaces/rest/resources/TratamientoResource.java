package org.example.vetalisbackend.clinical.interfaces.rest.resources;

public record TratamientoResource(
        Long id,
        Long consultaId,
        Long medicamentoId,
        String descripcion,
        String dosis,
        String frecuencia,
        String duracion
) {}
