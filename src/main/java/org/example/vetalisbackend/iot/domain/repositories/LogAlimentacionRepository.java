package org.example.vetalisbackend.iot.domain.repositories;

import org.example.vetalisbackend.iot.domain.model.entities.LogAlimentacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogAlimentacionRepository extends JpaRepository<LogAlimentacion, Long> {
    List<LogAlimentacion> findByDispensadorId(Long dispensadorId);
}
