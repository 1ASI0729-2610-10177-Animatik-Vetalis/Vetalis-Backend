package org.example.vetalisbackend.clinical.interfaces.rest.transform;

import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;
import org.example.vetalisbackend.clinical.interfaces.rest.resources.ConsultaResource;

public class ConsultaResourceFromEntityAssembler {
    public static ConsultaResource fromDomainModel(Consulta consulta) {
        return new ConsultaResource(
                consulta.getId(),
                consulta.getMascotaId(),
                consulta.getVeterinarioId(),
                consulta.getFecha(),
                consulta.getTipo(),
                consulta.getSubjetivo(),
                consulta.getObjetivo(),
                consulta.getEvaluacion(),
                consulta.getPlan(),
                consulta.getEstado(),
                consulta.getDiagnostico(),
                consulta.getTemperatura(),
                consulta.getCerrada()
        );
    }
}
