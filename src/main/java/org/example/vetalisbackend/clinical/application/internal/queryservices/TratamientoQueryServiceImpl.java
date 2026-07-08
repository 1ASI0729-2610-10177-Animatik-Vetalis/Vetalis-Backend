package org.example.vetalisbackend.clinical.application.internal.queryservices;

import org.example.vetalisbackend.clinical.application.queryservices.TratamientoQueryService;
import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;
import org.example.vetalisbackend.clinical.domain.repositories.TratamientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TratamientoQueryServiceImpl implements TratamientoQueryService {

    private final TratamientoRepository tratamientoRepository;

    public TratamientoQueryServiceImpl(TratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @Override
    public Optional<Tratamiento> findById(Long id) {
        return tratamientoRepository.findById(id);
    }

    @Override
    public List<Tratamiento> findAll() {
        return tratamientoRepository.findAll();
    }

    @Override
    public List<Tratamiento> findByConsultaId(Long consultaId) {
        return tratamientoRepository.findByConsultaId(consultaId);
    }
}
