package org.example.vetalisbackend.clients.application.internal.queryservices;

import org.example.vetalisbackend.clients.domain.model.aggregates.Cliente;
import org.example.vetalisbackend.clients.domain.repositories.ClienteRepository;
import org.example.vetalisbackend.clients.application.queryservices.ClienteQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteQueryServiceImpl implements ClienteQueryService {

    private final ClienteRepository clienteRepository;

    public ClienteQueryServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
