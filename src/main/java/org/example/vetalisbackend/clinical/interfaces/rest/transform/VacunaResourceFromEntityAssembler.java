package org.example.vetalisbackend.clinical.interfaces.rest.transform;

import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.VacunaResource;

public class VacunaResourceFromEntityAssembler {
    public static VacunaResource fromDomainModel(Vacuna vacuna) {
        return new VacunaResource(
                vacuna.getId(),
                vacuna.getMascotaId(),
                vacuna.getTipoVacunaId(),
                vacuna.getNombreVacuna(),
                vacuna.getLote(),
                vacuna.getFechaAplicacion(),
                vacuna.getProximaDosis(),
                vacuna.getEstado(),
                vacuna.getVeterinarioId()
        );
    }
}
