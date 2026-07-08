package org.example.vetalisbackend.inventory.interfaces.rest.resources;

import java.time.LocalDateTime;

public record PagoResource(
        Long id,
        Long consultaId,
        Long mascotaId,
        Double monto,
        Double montoOriginal,
        String descripcion,
        String metodoPago,
        String metodoPago2,
        Double monto2,
        LocalDateTime fechaPago,
        String estado,
        Long medicamentoId,
        Integer cantidad,
        Double descuento,
        Boolean anulado,
        String motivoAnulacion
) {}
