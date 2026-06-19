package org.example.vetalisbackend.scheduling.interfaces.rest.transform;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;
import org.example.vetalisbackend.scheduling.interfaces.rest.resources.CitaResource;

public class CitaResourceFromEntityAssembler {
    public static CitaResource fromDomainModel(Cita cita) {
        return new CitaResource(
                cita.getId(),
                cita.getMascotaId(),
                cita.getVeterinarioId(),
                cita.getFecha(),
                cita.getMotivo(),
                cita.getTipo(),
                cita.getEstado() != null ? cita.getEstado().name() : null
        );
    }
}
