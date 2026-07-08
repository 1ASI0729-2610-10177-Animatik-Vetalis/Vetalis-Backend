package org.example.vetalisbackend.scheduling.application.internal.commandservices;

import org.example.vetalisbackend.scheduling.domain.model.aggregates.Cita;
import org.example.vetalisbackend.scheduling.domain.model.commands.CreateCitaCommand;
import org.example.vetalisbackend.scheduling.domain.repositories.CitaRepository;
import org.example.vetalisbackend.scheduling.application.commandservices.CitaCommandService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CitaCommandServiceImpl implements CitaCommandService {

    private final CitaRepository citaRepository;

    public CitaCommandServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public Optional<Cita> handle(CreateCitaCommand command) {
        LocalDateTime ventanaInicio = command.fecha().minusMinutes(29);
        LocalDateTime ventanaFin = command.fecha().plusMinutes(29);
        boolean conflicto = citaRepository.existsByVeterinarioIdAndFechaBetween(
                command.veterinarioId(), ventanaInicio, ventanaFin);
        if (conflicto) return Optional.empty();
        Cita cita = new Cita(command.mascotaId(), command.veterinarioId(), command.fecha(),
                command.motivo(), command.tipo(), command.estado());
        return Optional.of(citaRepository.save(cita));
    }

    @Override
    public Optional<Cita> update(Long id, CreateCitaCommand command) {
        return citaRepository.findById(id).map(c -> {
            c.setMascotaId(command.mascotaId());
            c.setVeterinarioId(command.veterinarioId());
            c.setFecha(command.fecha());
            c.setMotivo(command.motivo());
            c.setTipo(command.tipo());
            c.setEstado(command.estado());
            return citaRepository.save(c);
        });
    }

    @Override
    public void deleteById(Long id) {
        citaRepository.deleteById(id);
    }
}
