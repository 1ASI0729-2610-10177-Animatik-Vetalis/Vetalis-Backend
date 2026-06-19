package org.example.vetalisbackend.iot.application.queryservices;

import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;

import java.util.List;
import java.util.Optional;

public interface DispensadorQueryService {
    Optional<DispensadorIoT> findById(Long id);
    List<DispensadorIoT> findAll();
}
