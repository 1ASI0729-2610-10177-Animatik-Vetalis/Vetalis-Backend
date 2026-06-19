package org.example.vetalisbackend.clinical.application.internal.queryservices;

import org.example.vetalisbackend.clinical.application.queryservices.ConsultaQueryService;
import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;
import org.example.vetalisbackend.clinical.domain.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaQueryServiceImpl implements ConsultaQueryService {

    private final ConsultaRepository consultaRepository;

    public ConsultaQueryServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Optional<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    @Override
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    @Override
    public List<Consulta> findByMascotaId(Long mascotaId) {
        return consultaRepository.findByMascotaId(mascotaId);
    }
}
