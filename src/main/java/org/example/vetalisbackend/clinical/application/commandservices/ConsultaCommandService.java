package org.example.vetalisbackend.clinical.application.commandservices;

import org.example.vetalisbackend.clinical.domain.model.commands.CreateConsultaCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;

import java.util.Optional;

public interface ConsultaCommandService {
    Optional<Consulta> handle(CreateConsultaCommand command);
    Optional<Consulta> update(Long id, CreateConsultaCommand command);
    void deleteById(Long id);
}
