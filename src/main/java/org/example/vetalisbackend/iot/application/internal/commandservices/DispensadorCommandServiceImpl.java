package org.example.vetalisbackend.iot.application.internal.commandservices;

import org.example.vetalisbackend.iot.application.commandservices.DispensadorCommandService;
import org.example.vetalisbackend.iot.domain.model.aggregates.DispensadorIoT;
import org.example.vetalisbackend.iot.domain.model.commands.CreateDispensadorCommand;
import org.example.vetalisbackend.iot.domain.model.entities.LogAlimentacion;
import org.example.vetalisbackend.iot.domain.model.enums.EstadoDispensador;
import org.example.vetalisbackend.iot.domain.repositories.DispensadorRepository;
import org.example.vetalisbackend.iot.domain.repositories.LogAlimentacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DispensadorCommandServiceImpl implements DispensadorCommandService {

    private final DispensadorRepository dispensadorRepository;
    private final LogAlimentacionRepository logAlimentacionRepository;

    public DispensadorCommandServiceImpl(DispensadorRepository dispensadorRepository,
                                         LogAlimentacionRepository logAlimentacionRepository) {
        this.dispensadorRepository = dispensadorRepository;
        this.logAlimentacionRepository = logAlimentacionRepository;
    }

    @Override
    public Optional<DispensadorIoT> handle(CreateDispensadorCommand command) {
        DispensadorIoT d = new DispensadorIoT(command.numeroSerie(), command.modelo(),
                command.estado(), command.nivelAlimento(), command.ultimaConexion());
        d.setMascotaId(command.mascotaId());
        return Optional.of(dispensadorRepository.save(d));
    }

    @Override
    public Optional<DispensadorIoT> update(Long id, CreateDispensadorCommand command) {
        return dispensadorRepository.findById(id).map(d -> {
            d.setNumeroSerie(command.numeroSerie());
            d.setModelo(command.modelo());
            d.setEstado(command.estado());
            d.setNivelAlimento(command.nivelAlimento());
            d.setUltimaConexion(command.ultimaConexion());
            d.setMascotaId(command.mascotaId());
            return dispensadorRepository.save(d);
        });
    }

    @Override
    public Optional<DispensadorIoT> dispensar(Long id, Double cantidad) {
        return dispensadorRepository.findById(id).map(d -> {
            boolean exito = false;
            if (d.getNivelAlimento() != null && d.getNivelAlimento() >= cantidad) {
                d.setNivelAlimento(d.getNivelAlimento() - cantidad);
                d.setUltimaConexion(LocalDateTime.now());
                if (d.getNivelAlimento() <= 0) {
                    d.setEstado(EstadoDispensador.SIN_ALIMENTO);
                }
                exito = true;
            }
            LogAlimentacion log = new LogAlimentacion(id, LocalDateTime.now(), cantidad, exito);
            logAlimentacionRepository.save(log);
            return dispensadorRepository.save(d);
        });
    }

    @Override
    public void deleteById(Long id) {
        dispensadorRepository.deleteById(id);
    }
}
