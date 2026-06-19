package org.example.vetalisbackend.clinical.interfaces.rest.transform;

import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.HospitalizacionResource;

public class HospitalizacionResourceFromEntityAssembler {
    public static HospitalizacionResource fromDomainModel(Hospitalizacion h) {
        return new HospitalizacionResource(
                h.getId(),
                h.getMascotaId(),
                h.getFechaIngreso(),
                h.getFechaSalida(),
                h.getMotivo(),
                h.getDiagnostico(),
                h.getTratamiento(),
                h.getEstado(),
                h.getVeterinarioId()
        );
    }
}
