package org.example.vetalisbackend.iot.interfaces.rest.transform;

import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;
import org.example.vetalisbackend.iot.interfaces.rest.resources.DispensadorResource;

public class DispensadorResourceFromEntityAssembler {
    public static DispensadorResource fromDomainModel(DispensadorIoT d) {
        return new DispensadorResource(
                d.getId(),
                d.getNumeroSerie(),
                d.getModelo(),
                d.getEstado() != null ? d.getEstado().name() : null,
                d.getNivelAlimento(),
                d.getUltimaConexion(),
                d.getMascotaId()
        );
    }
}
