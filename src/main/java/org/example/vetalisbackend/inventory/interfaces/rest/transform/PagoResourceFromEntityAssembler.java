package org.example.vetalisbackend.inventory.interfaces.rest.transform;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.PagoResource;

public class PagoResourceFromEntityAssembler {
    public static PagoResource fromDomainModel(Pago p) {
        return new PagoResource(
                p.getId(), p.getConsultaId(), p.getMonto(),
                p.getMetodoPago(), p.getFechaPago(), p.getEstado()
        );
    }
}
