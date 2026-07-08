package org.example.vetalisbackend.inventory.interfaces.rest.resources;

import java.time.LocalDateTime;

public record PagoResource(
        Long id,
        Long consultaId,
        Double monto,
        String metodoPago,
        LocalDateTime fechaPago,
        String estado,
        Long medicamentoId,
        Integer cantidad,
        Double descuento,
        Boolean anulado,
        String motivoAnulacion
) {}
