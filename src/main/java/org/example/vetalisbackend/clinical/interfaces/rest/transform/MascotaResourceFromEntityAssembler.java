package org.example.vetalisbackend.clinical.interfaces.rest.transform;

import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.MascotaResource;

public class MascotaResourceFromEntityAssembler {
    public static MascotaResource fromDomainModel(Mascota mascota) {
        return new MascotaResource(
                mascota.getId(),
                mascota.getNombre(),
                mascota.getSexo(),
                mascota.getFechaNacimiento(),
                mascota.getPeso(),
                mascota.getEstado(),
                mascota.getClienteId(),
                mascota.getRazaId()
        );
    }
}
