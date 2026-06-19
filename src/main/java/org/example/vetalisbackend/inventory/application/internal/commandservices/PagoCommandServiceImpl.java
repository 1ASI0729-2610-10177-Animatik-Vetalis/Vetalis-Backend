package org.example.vetalisbackend.inventory.application.internal.commandservices;

import org.example.vetalisbackend.inventory.domain.model.commands.CreatePagoCommand;
import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.example.vetalisbackend.inventory.domain.repositories.PagoRepository;
import org.example.vetalisbackend.inventory.application.commandservices.PagoCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoCommandServiceImpl implements PagoCommandService {

    private final PagoRepository pagoRepository;

    public PagoCommandServiceImpl(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Optional<Pago> handle(CreatePagoCommand command) {
        Pago pago = new Pago(command.consultaId(), command.monto(), command.metodoPago(),
                command.fechaPago(), command.estado());
        return Optional.of(pagoRepository.save(pago));
    }

    @Override
    public Optional<Pago> update(Long id, CreatePagoCommand command) {
        return pagoRepository.findById(id).map(p -> {
            p.setConsultaId(command.consultaId());
            p.setMonto(command.monto());
            p.setMetodoPago(command.metodoPago());
            p.setFechaPago(command.fechaPago());
            p.setEstado(command.estado());
            return pagoRepository.save(p);
        });
    }

    @Override
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }
}
