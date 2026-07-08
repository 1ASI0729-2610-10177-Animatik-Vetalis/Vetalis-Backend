package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByClienteId(Long clienteId);
    List<Mascota> findByNombreContainingIgnoreCase(String nombre);
    long countByEstado(String estado);
}
