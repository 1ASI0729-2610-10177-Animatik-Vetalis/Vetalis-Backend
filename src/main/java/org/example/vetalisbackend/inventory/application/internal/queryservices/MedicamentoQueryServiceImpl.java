package org.example.vetalisbackend.inventory.application.internal.queryservices;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.example.vetalisbackend.inventory.domain.repositories.MedicamentoRepository;
import org.example.vetalisbackend.inventory.application.queryservices.MedicamentoQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoQueryServiceImpl implements MedicamentoQueryService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoQueryServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Optional<Medicamento> findById(Long id) {
        return medicamentoRepository.findById(id);
    }

    @Override
    public List<Medicamento> findAll() {
        return medicamentoRepository.findAll();
    }

    @Override
    public List<Medicamento> findLowStock() {
        return medicamentoRepository.findLowStock();
    }
}
