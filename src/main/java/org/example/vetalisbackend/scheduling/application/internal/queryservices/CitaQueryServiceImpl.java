package org.example.vetalisbackend.scheduling.application.internal.queryservices;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;
import org.example.vetalisbackend.scheduling.domain.repositories.CitaRepository;
import org.example.vetalisbackend.scheduling.application.queryservices.CitaQueryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaQueryServiceImpl implements CitaQueryService {

    private final CitaRepository citaRepository;

    public CitaQueryServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Optional<Cita> findById(Long id) {
        return citaRepository.findById(id);
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Override
    public List<Cita> findByFechaAndVeterinarioId(LocalDate fecha, Long veterinarioId) {
        LocalDateTime start = fecha.atStartOfDay();
        LocalDateTime end = fecha.atTime(23, 59, 59);
        if (veterinarioId != null) {
            return citaRepository.findByFechaBetweenAndVeterinarioId(start, end, veterinarioId);
        }
        return citaRepository.findByFechaBetween(start, end);
    }

    @Override
    public List<Cita> findProximas() {
        return citaRepository.findByFechaAfterOrderByFechaAsc(LocalDateTime.now());
    }
}
