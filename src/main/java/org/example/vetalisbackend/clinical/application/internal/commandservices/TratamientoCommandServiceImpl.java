package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.TratamientoCommandService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateTratamientoCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Tratamiento;
import org.example.vetalisbackend.clinical.domain.repositories.TratamientoRepository;
import org.example.vetalisbackend.inventory.domain.repositories.MedicamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TratamientoCommandServiceImpl implements TratamientoCommandService {

    private final TratamientoRepository tratamientoRepository;
    private final MedicamentoRepository medicamentoRepository;

    public TratamientoCommandServiceImpl(TratamientoRepository tratamientoRepository,
                                         MedicamentoRepository medicamentoRepository) {
        this.tratamientoRepository = tratamientoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    @Transactional
    public Optional<Tratamiento> handle(CreateTratamientoCommand command) {
        if (command.medicamentoId() != null) {
            int unidades = command.cantidad() != null && command.cantidad() > 0 ? command.cantidad() : 1;
            medicamentoRepository.findById(command.medicamentoId()).ifPresent(med -> {
                int nuevoStock = Math.max(0, med.getStockActual() - unidades);
                med.setStockActual(nuevoStock);
                medicamentoRepository.save(med);
            });
        }
        Tratamiento t = new Tratamiento(command.consultaId(), command.medicamentoId(),
                command.cantidad(), command.descripcion(), command.dosis(),
                command.frecuencia(), command.duracion());
        return Optional.of(tratamientoRepository.save(t));
    }

    @Override
    @Transactional
    public Optional<Tratamiento> update(Long id, CreateTratamientoCommand command) {
        return tratamientoRepository.findById(id).map(t -> {
            t.setConsultaId(command.consultaId());
            t.setMedicamentoId(command.medicamentoId());
            t.setCantidad(command.cantidad());
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
