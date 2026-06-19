package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.VacunaCommandService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateVacunaCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Vacuna;
import org.example.vetalisbackend.clinical.domain.repositories.VacunaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacunaCommandServiceImpl implements VacunaCommandService {

    private final VacunaRepository vacunaRepository;

    public VacunaCommandServiceImpl(VacunaRepository vacunaRepository) {
        this.vacunaRepository = vacunaRepository;
    }

    @Override
    public Optional<Vacuna> handle(CreateVacunaCommand command) {
        Vacuna vacuna = new Vacuna(command.mascotaId(), command.tipoVacunaId(), command.nombreVacuna(),
                command.lote(), command.fechaAplicacion(), command.proximaDosis(),
                command.estado(), command.veterinarioId());
        return Optional.of(vacunaRepository.save(vacuna));
    }

    @Override
    public Optional<Vacuna> update(Long id, CreateVacunaCommand command) {
        return vacunaRepository.findById(id).map(v -> {
            v.setMascotaId(command.mascotaId());
            v.setTipoVacunaId(command.tipoVacunaId());
            v.setNombreVacuna(command.nombreVacuna());
            v.setLote(command.lote());
            v.setFechaAplicacion(command.fechaAplicacion());
            v.setProximaDosis(command.proximaDosis());
            v.setEstado(command.estado());
            v.setVeterinarioId(command.veterinarioId());
            return vacunaRepository.save(v);
        });
    }

    @Override
    public void deleteById(Long id) {
        vacunaRepository.deleteById(id);
    }
}
