package org.example.vetalisbackend.inventory.application.internal.queryservices;

import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.example.vetalisbackend.inventory.domain.repositories.PagoRepository;
import org.example.vetalisbackend.inventory.application.queryservices.PagoQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoQueryServiceImpl implements PagoQueryService {

    private final PagoRepository pagoRepository;

    public PagoQueryServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Optional<Pago> findById(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public List<Pago> findByConsultaId(Long consultaId) {
        return pagoRepository.findByConsultaId(consultaId);
    }
}
