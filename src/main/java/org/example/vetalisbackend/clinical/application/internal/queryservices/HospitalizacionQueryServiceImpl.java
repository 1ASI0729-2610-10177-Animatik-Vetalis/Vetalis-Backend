package org.example.vetalisbackend.clinical.application.internal.queryservices;

import org.example.vetalisbackend.clinical.application.queryservices.HospitalizacionQueryService;
import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;
import org.example.vetalisbackend.clinical.domain.repositories.HospitalizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalizacionQueryServiceImpl implements HospitalizacionQueryService {

    private final HospitalizacionRepository hospitalizacionRepository;

    public HospitalizacionQueryServiceImpl(HospitalizacionRepository hospitalizacionRepository) {
        this.hospitalizacionRepository = hospitalizacionRepository;
    }

    @Override
    public Optional<Hospitalizacion> findById(Long id) {
        return hospitalizacionRepository.findById(id);
    }

    @Override
    public List<Hospitalizacion> findAll() {
        return hospitalizacionRepository.findAll();
    }

    @Override
    public List<Hospitalizacion> findByEstado(String estado) {
        return hospitalizacionRepository.findByEstado(estado);
    }

    @Override
    public List<Hospitalizacion> findByMascotaId(Long mascotaId) {
        return hospitalizacionRepository.findByMascotaId(mascotaId);
    }
}
