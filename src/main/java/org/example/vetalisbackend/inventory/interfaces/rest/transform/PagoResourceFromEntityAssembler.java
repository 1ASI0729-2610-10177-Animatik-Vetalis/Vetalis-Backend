package org.example.vetalisbackend.inventory.interfaces.rest.transform;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.PagoResource;

public class PagoResourceFromEntityAssembler {
    public static PagoResource fromDomainModel(Pago p) {
        return new PagoResource(
                p.getId(), p.getConsultaId(), p.getMascotaId(),
                p.getMonto(), p.getMontoOriginal(), p.getDescripcion(),
                p.getMetodoPago(), p.getMetodoPago2(), p.getMonto2(),
                p.getFechaPago(), p.getEstado(),
                p.getMedicamentoId(), p.getCantidad(), p.getDescuento(),
                p.getAnulado(), p.getMotivoAnulacion()
        );
    }
}
