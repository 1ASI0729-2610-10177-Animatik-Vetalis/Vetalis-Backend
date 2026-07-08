package org.example.vetalisbackend.inventory.application.internal.commandservices;

import org.example.vetalisbackend.inventory.domain.model.aggregates.Medicamento;
import org.example.vetalisbackend.inventory.domain.model.commands.CreatePagoCommand;
import org.example.vetalisbackend.inventory.domain.model.entities.Pago;
import org.example.vetalisbackend.inventory.domain.repositories.MedicamentoRepository;
import org.example.vetalisbackend.inventory.domain.repositories.PagoRepository;
import org.example.vetalisbackend.inventory.application.commandservices.PagoCommandService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PagoCommandServiceImpl implements PagoCommandService {

    private final PagoRepository pagoRepository;
    private final MedicamentoRepository medicamentoRepository;

    public PagoCommandServiceImpl(PagoRepository pagoRepository, MedicamentoRepository medicamentoRepository) {
        this.pagoRepository = pagoRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Override
    @Transactional
    public Optional<Pago> handle(CreatePagoCommand command) {
        if (command.medicamentoId() != null && command.cantidad() != null) {
            Medicamento med = medicamentoRepository.findById(command.medicamentoId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado: " + command.medicamentoId()));
            int nuevoStock = med.getStockActual() - command.cantidad();
            if (nuevoStock < 0) throw new IllegalStateException("Stock insuficiente para el medicamento: " + med.getNombre());
            med.setStockActual(nuevoStock);
            medicamentoRepository.save(med);
        }
        Pago pago = new Pago(command.consultaId(), command.monto(), command.metodoPago(),
                command.fechaPago(), command.estado(), command.medicamentoId(),
                command.cantidad(), command.descuento());
        return Optional.of(pagoRepository.save(pago));
    }

    @Override
    @Transactional
    public Optional<Pago> anular(Long id, String motivo) {
        return pagoRepository.findById(id).flatMap(p -> {
            if (Boolean.TRUE.equals(p.getAnulado())) return Optional.empty();
            if (p.getMedicamentoId() != null && p.getCantidad() != null) {
                medicamentoRepository.findById(p.getMedicamentoId()).ifPresent(med -> {
                    med.setStockActual(med.getStockActual() + p.getCantidad());
                    medicamentoRepository.save(med);
                });
            }
            p.setAnulado(true);
            p.setMotivoAnulacion(motivo);
            p.setEstado("ANULADO");
            return Optional.of(pagoRepository.save(p));
        });
    }

    @Override
    @Transactional
    public Optional<Pago> update(Long id, CreatePagoCommand command) {
        return pagoRepository.findById(id).flatMap(p -> {
            if (Boolean.TRUE.equals(p.getAnulado())) return Optional.empty();
            p.setConsultaId(command.consultaId());
            p.setMonto(command.monto());
            p.setMetodoPago(command.metodoPago());
            p.setFechaPago(command.fechaPago());
            p.setEstado(command.estado());
            return Optional.of(pagoRepository.save(p));
        });
    }

    @Override
    public void deleteById(Long id) {
        pagoRepository.deleteById(id);
    }
}
