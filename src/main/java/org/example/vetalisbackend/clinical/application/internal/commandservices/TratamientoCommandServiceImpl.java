package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.TratamientoCommandService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateTratamientoCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;
import org.example.vetalisbackend.clinical.domain.repositories.TratamientoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TratamientoCommandServiceImpl implements TratamientoCommandService {

    private final TratamientoRepository tratamientoRepository;

    public TratamientoCommandServiceImpl(TratamientoRepository tratamientoRepository) {
        this.tratamientoRepository = tratamientoRepository;
    }

    @Override
    public Optional<Tratamiento> handle(CreateTratamientoCommand command) {
        Tratamiento t = new Tratamiento(command.consultaId(), command.medicamentoId(),
                command.descripcion(), command.dosis(), command.frecuencia(), command.duracion());
        return Optional.of(tratamientoRepository.save(t));
    }

    @Override
    public Optional<Tratamiento> update(Long id, CreateTratamientoCommand command) {
        return tratamientoRepository.findById(id).map(t -> {
            t.setConsultaId(command.consultaId());
            t.setMedicamentoId(command.medicamentoId());
            t.setDescripcion(command.descripcion());
            t.setDosis(command.dosis());
            t.setFrecuencia(command.frecuencia());
            t.setDuracion(command.duracion());
            return tratamientoRepository.save(t);
        });
    }

    @Override
    public void deleteById(Long id) {
        tratamientoRepository.deleteById(id);
    }
}
