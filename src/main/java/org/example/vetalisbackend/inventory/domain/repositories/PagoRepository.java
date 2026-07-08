package org.example.vetalisbackend.inventory.domain.repositories;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByConsultaId(Long consultaId);
    List<Pago> findByAnuladoFalseAndFechaPagoBetween(LocalDateTime desde, LocalDateTime hasta);

    @Query("SELECT COALESCE(SUM(p.monto), 0) FROM Pago p WHERE p.anulado = false AND p.fechaPago BETWEEN :desde AND :hasta")
    Double sumMontoByFechaPagoBetween(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query("SELECT p.metodoPago, SUM(p.monto) FROM Pago p WHERE p.anulado = false AND p.fechaPago BETWEEN :desde AND :hasta GROUP BY p.metodoPago")
    List<Object[]> sumByMetodoPago(@Param("desde") LocalDateTime desde, @Param("hasta") LocalDateTime hasta);

    @Query(value = "SELECT p.* FROM pagos p INNER JOIN consultas c ON p.consulta_id = c.id WHERE p.anulado = false AND c.veterinario_id = :veterinarioId AND p.fecha_pago BETWEEN :desde AND :hasta", nativeQuery = true)
    List<Pago> findByVeterinarioIdAndFechaPagoBetween(@Param("veterinarioId") Long veterinarioId,
                                                       @Param("desde") LocalDateTime desde,
                                                       @Param("hasta") LocalDateTime hasta);
}
