package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
    List<Vacuna> findByMascotaId(Long mascotaId);
    List<Vacuna> findByEstadoIn(List<String> estados);
    List<Vacuna> findByProximaDosisBeforeAndEstado(LocalDate date, String estado);
    long countByFechaAplicacionBetween(LocalDate start, LocalDate end);
}
