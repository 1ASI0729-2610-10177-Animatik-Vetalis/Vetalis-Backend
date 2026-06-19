package org.example.vetalisbackend.inventory.domain.repositories;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByConsultaId(Long consultaId);
}
