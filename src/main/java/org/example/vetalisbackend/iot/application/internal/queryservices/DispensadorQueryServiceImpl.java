package org.example.vetalisbackend.iot.application.internal.queryservices;

import org.example.vetalisbackend.iot.application.queryservices.DispensadorQueryService;
import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;
import org.example.vetalisbackend.iot.domain.repositories.DispensadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispensadorQueryServiceImpl implements DispensadorQueryService {

    private final DispensadorRepository dispensadorRepository;

    public DispensadorQueryServiceImpl(DispensadorRepository dispensadorRepository) {
        this.dispensadorRepository = dispensadorRepository;
    }

    @Override
    public Optional<DispensadorIoT> findById(Long id) {
        return dispensadorRepository.findById(id);
    }

    @Override
    public List<DispensadorIoT> findAll() {
        return dispensadorRepository.findAll();
    }
}
