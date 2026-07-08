package org.example.vetalisbackend.scheduling.domain.repositories;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByFechaBetween(LocalDateTime start, LocalDateTime end);
    List<Cita> findByFechaBetweenAndVeterinarioId(LocalDateTime start, LocalDateTime end, Long veterinarioId);
    List<Cita> findByFechaAfterOrderByFechaAsc(LocalDateTime fecha);
    long countByFechaBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByVeterinarioIdAndFechaBetween(Long veterinarioId, LocalDateTime start, LocalDateTime end);
}
