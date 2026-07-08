package org.example.vetalisbackend.clinical.interfaces.rest.transform;

import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.TratamientoResource;

public class TratamientoResourceFromEntityAssembler {
    public static TratamientoResource fromDomainModel(Tratamiento t) {
        return new TratamientoResource(
                t.getId(), t.getConsultaId(), t.getMedicamentoId(), t.getCantidad(),
                t.getDescripcion(), t.getDosis(), t.getFrecuencia(), t.getDuracion()
        );
    }
}
