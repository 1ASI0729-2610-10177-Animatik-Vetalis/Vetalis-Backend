package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalizacionRepository extends JpaRepository<Hospitalizacion, Long> {
    List<Hospitalizacion> findByMascotaId(Long mascotaId);
    List<Hospitalizacion> findByEstado(String estado);
    long countByFechaSalidaIsNull();
}
