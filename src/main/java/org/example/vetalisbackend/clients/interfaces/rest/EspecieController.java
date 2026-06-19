package org.example.vetalisbackend.clients.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.clients.domain.repositories.EspecieRepository;
import org.example.vetalisbackend.clients.interfaces.rest.resources.EspecieResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especies")
@CrossOrigin(origins = "*")
public class EspecieController {

    private final EspecieRepository especieRepository;

    public EspecieController(EspecieRepository especieRepository) {
        this.especieRepository = especieRepository;
    }

    @GetMapping
    public ResponseEntity<List<EspecieResource>> getAll() {
        List<EspecieResource> resources = especieRepository.findAll().stream()
                .map(e -> new EspecieResource(e.getId(), e.getNombre()))
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<EspecieResource> create(@RequestBody @Valid EspecieResource resource) {
        var especie = new org.example.vetalisbackend.clients.domain.model.entities.Especie(resource.nombre());
        var saved = especieRepository.save(especie);
        return ResponseEntity.status(201).body(new EspecieResource(saved.getId(), saved.getNombre()));
    }
}
