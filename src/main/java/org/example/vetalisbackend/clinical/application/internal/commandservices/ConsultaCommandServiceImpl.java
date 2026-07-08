package org.example.vetalisbackend.clinical.application.internal.commandservices;

import org.example.vetalisbackend.clinical.application.commandservices.ConsultaCommandService;
import org.example.vetalisbackend.clinical.domain.model.commands.CreateConsultaCommand;
import org.example.vetalisbackend.clinical.domain.model.entities.Consulta;
import org.example.vetalisbackend.clinical.domain.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaCommandServiceImpl implements ConsultaCommandService {

    private final ConsultaRepository consultaRepository;

    public ConsultaCommandServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public Optional<Consulta> handle(CreateConsultaCommand command) {
        Consulta consulta = new Consulta(command.mascotaId(), command.veterinarioId(), command.fecha(),
                command.tipo(), command.subjetivo(), command.objetivo(), command.evaluacion(),
                command.plan(), command.estado(), command.diagnostico(), command.temperatura());
        return Optional.of(consultaRepository.save(consulta));
    }

    @Override
    public Optional<Consulta> update(Long id, CreateConsultaCommand command) {
        return consultaRepository.findById(id).flatMap(c -> {
            if (Boolean.TRUE.equals(c.getCerrada())) return Optional.empty();
            c.setMascotaId(command.mascotaId());
            c.setVeterinarioId(command.veterinarioId());
            c.setFecha(command.fecha());
            c.setTipo(command.tipo());
            c.setSubjetivo(command.subjetivo());
            c.setObjetivo(command.objetivo());
            c.setEvaluacion(command.evaluacion());
            c.setPlan(command.plan());
            c.setEstado(command.estado());
            c.setDiagnostico(command.diagnostico());
            c.setTemperatura(command.temperatura());
            return Optional.of(consultaRepository.save(c));
        });
    }

    @Override
    public Optional<Consulta> cerrar(Long id) {
        return consultaRepository.findById(id).map(c -> {
            c.setCerrada(true);
            c.setEstado("CERRADA");
            return consultaRepository.save(c);
        });
    }

    @Override
    public void deleteById(Long id) {
        consultaRepository.deleteById(id);
    }
}
