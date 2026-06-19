package org.example.vetalisbackend.clinical.domain.repositories;

import org.example.vetalisbackend.clinical.domain.model.entities.MedicacionHospitalaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicacionHospitalariaRepository extends JpaRepository<MedicacionHospitalaria, Long> {
    List<MedicacionHospitalaria> findByHospitalizacionId(Long hospitalizacionId);
}
