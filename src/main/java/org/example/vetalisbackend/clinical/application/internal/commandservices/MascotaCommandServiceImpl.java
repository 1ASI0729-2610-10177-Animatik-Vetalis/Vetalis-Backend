package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.MascotaCommandService;
import org.example.vetalisbackend.clinical.domain.model.aggregates.Mascota;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateMascotaCommand;
import org.example.vetalisbackend.clinical.domain.repositories.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MascotaCommandServiceImpl implements MascotaCommandService {

    private final MascotaRepository mascotaRepository;

    public MascotaCommandServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Optional<Mascota> handle(CreateMascotaCommand command) {
        Mascota mascota = new Mascota(command.nombre(), command.sexo(), command.fechaNacimiento(),
                command.peso(), command.estado(), command.clienteId(), command.razaId());
        return Optional.of(mascotaRepository.save(mascota));
    }

    @Override
    public Optional<Mascota> update(Long id, CreateMascotaCommand command) {
        return mascotaRepository.findById(id).map(m -> {
            m.setNombre(command.nombre());
            m.setSexo(command.sexo());
            m.setFechaNacimiento(command.fechaNacimiento());
            m.setPeso(command.peso());
            m.setEstado(command.estado());
            m.setClienteId(command.clienteId());
            m.setRazaId(command.razaId());
            return mascotaRepository.save(m);
        });
    }

    @Override
    public void deleteById(Long id) {
        mascotaRepository.deleteById(id);
    }
}
