package org.example.vetalisbackend.clinical.application.internal.queryservices;

import org.example.vetalisbackend.clinical.application.queryservices.MascotaQueryService;
import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;
import org.example.vetalisbackend.clinical.domain.repositories.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaQueryServiceImpl implements MascotaQueryService {

    private final MascotaRepository mascotaRepository;

    public MascotaQueryServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Optional<Mascota> findById(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public List<Mascota> findByClienteId(Long clienteId) {
        return mascotaRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Mascota> findByNombre(String nombre) {
        return mascotaRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
