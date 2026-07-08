package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMascotaId(Long mascotaId);
    List<Consulta> findByMascotaIdOrderByFechaDesc(Long mascotaId);
    List<Consulta> findByVeterinarioId(Long veterinarioId);
    List<Consulta> findByMascotaIdAndTipo(Long mascotaId, String tipo);
}
