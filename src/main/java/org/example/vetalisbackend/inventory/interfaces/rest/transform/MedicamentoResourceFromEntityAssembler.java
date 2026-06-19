package org.example.vetalisbackend.inventory.interfaces.rest.transform;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.example.vetalisbackend.inventory.interfaces.rest.resources.MedicamentoResource;

public class MedicamentoResourceFromEntityAssembler {
    public static MedicamentoResource fromDomainModel(Medicamento m) {
        return new MedicamentoResource(
                m.getId(), m.getNombre(), m.getDescripcion(),
                m.getPrecioUnitario(), m.getStockActual(), m.getPuntoReorden()
        );
    }
}
