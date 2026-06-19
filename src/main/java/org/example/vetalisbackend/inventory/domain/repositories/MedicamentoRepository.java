package org.example.vetalisbackend.inventory.domain.repositories;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    @Query("SELECT m FROM Medicamento m WHERE m.stockActual <= m.puntoReorden")
    List<Medicamento> findLowStock();
}
