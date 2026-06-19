package org.example.vetalisbackend.iot.domain.repositories;

import org.example.vetalisbackend.iot.domain.model.entities.ConfiguracionDieta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfiguracionDietaRepository extends JpaRepository<ConfiguracionDieta, Long> {
    List<ConfiguracionDieta> findByDispensadorId(Long dispensadorId);
    List<ConfiguracionDieta> findByHospitalizacionId(Long hospitalizacionId);
}
