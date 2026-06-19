package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.HospitalizacionCommandService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateHospitalizacionCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Hospitalizacion;
import org.example.vetalisbackend.clinical.domain.repositories.HospitalizacionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HospitalizacionCommandServiceImpl implements HospitalizacionCommandService {

    private final HospitalizacionRepository hospitalizacionRepository;

    public HospitalizacionCommandServiceImpl(HospitalizacionRepository hospitalizacionRepository) {
        this.hospitalizacionRepository = hospitalizacionRepository;
    }

    @Override
    public Optional<Hospitalizacion> handle(CreateHospitalizacionCommand command) {
        Hospitalizacion h = new Hospitalizacion(command.mascotaId(), command.fechaIngreso(),
                command.fechaSalida(), command.motivo(), command.diagnostico(),
                command.tratamiento(), command.estado(), command.veterinarioId());
        return Optional.of(hospitalizacionRepository.save(h));
    }

    @Override
    public Optional<Hospitalizacion> update(Long id, CreateHospitalizacionCommand command) {
        return hospitalizacionRepository.findById(id).map(h -> {
            h.setMascotaId(command.mascotaId());
            h.setFechaIngreso(command.fechaIngreso());
            h.setFechaSalida(command.fechaSalida());
            h.setMotivo(command.motivo());
            h.setDiagnostico(command.diagnostico());
            h.setTratamiento(command.tratamiento());
            h.setEstado(command.estado());
            h.setVeterinarioId(command.veterinarioId());
            return hospitalizacionRepository.save(h);
        });
    }

    @Override
    public void deleteById(Long id) {
        hospitalizacionRepository.deleteById(id);
    }
}
