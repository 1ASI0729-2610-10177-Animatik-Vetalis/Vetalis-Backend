package org.example.vetalisbackend.inventory.application.internal.commandservices;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.example.vetalisbackend.inventory.domain.model.commands.CreateMedicamentoCommand;
import org.example.vetalisbackend.inventory.domain.repositories.MedicamentoRepository;
import org.example.vetalisbackend.inventory.application.commandservices.MedicamentoCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicamentoCommandServiceImpl implements MedicamentoCommandService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoCommandServiceImpl(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    public Optional<Medicamento> handle(CreateMedicamentoCommand command) {
        Medicamento m = new Medicamento(command.nombre(), command.descripcion(),
                command.precioUnitario(), command.stockActual(), command.puntoReorden());
        return Optional.of(medicamentoRepository.save(m));
    }

    @Override
    public Optional<Medicamento> update(Long id, CreateMedicamentoCommand command) {
        return medicamentoRepository.findById(id).map(m -> {
            m.setNombre(command.nombre());
            m.setDescripcion(command.descripcion());
            m.setPrecioUnitario(command.precioUnitario());
            m.setStockActual(command.stockActual());
            m.setPuntoReorden(command.puntoReorden());
            return medicamentoRepository.save(m);
        });
    }

    @Override
    public void deleteById(Long id) {
        medicamentoRepository.deleteById(id);
    }
}
