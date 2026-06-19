package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TratamientoRepository extends JpaRepository<Tratamiento, Long> {
    List<Tratamiento> findByConsultaId(Long consultaId);
}
