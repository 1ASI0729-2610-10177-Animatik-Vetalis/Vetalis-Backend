package org.example.vetalisbackend.clinical.application.internal.queryservices;

import org.example.vetalisbackend.clinical.application.queryservices.VacunaQueryService;
import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;
import org.example.vetalisbackend.clinical.domain.repositories.VacunaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacunaQueryServiceImpl implements VacunaQueryService {

    private final VacunaRepository vacunaRepository;

    public VacunaQueryServiceImpl(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    @Override
    public Optional<Vacuna> findById(Long id) {
        return vacunaRepository.findById(id);
    }

    @Override
    public List<Vacuna> findAll() {
        return vacunaRepository.findAll();
    }

    @Override
    public List<Vacuna> findByMascotaId(Long mascotaId) {
        return vacunaRepository.findByMascotaId(mascotaId);
    }

    @Override
    public List<Vacuna> findAlerts() {
        return vacunaRepository.findByEstadoIn(List.of("PROXIMA", "VENCIDA"));
    }
}
