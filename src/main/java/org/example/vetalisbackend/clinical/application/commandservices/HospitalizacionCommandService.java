package org.example.vetalisbackend.clinical.application.commandservices;

import org.example.vetalisbackend.clinical.domain.model.commands.CreateHospitalizacionCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;

import java.util.Optional;

public interface HospitalizacionCommandService {
    Optional<Hospitalizacion> handle(CreateHospitalizacionCommand command);
    Optional<Hospitalizacion> update(Long id, CreateHospitalizacionCommand command);
    void deleteById(Long id);
}
