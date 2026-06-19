package org.example.vetalisbackend.clients.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.clients.domain.model.entities.Raza;
import org.example.vetalisbackend.clients.domain.repositories.RazaRepository;
import org.example.vetalisbackend.clients.interfaces.rest.resources.RazaResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/razas")
@CrossOrigin(origins = "*")
public class RazaController {

    private final RazaRepository razaRepository;

    public RazaController(RazaRepository razaRepository) {
        this.razaRepository = razaRepository;
    }

    @GetMapping
    public ResponseEntity<List<RazaResource>> getAll(@RequestParam(required = false) Long especieId) {
        List<Raza> razas = (especieId != null)
                ? razaRepository.findByEspecieId(especieId)
                : razaRepository.findAll();
        List<RazaResource> resources = razas.stream()
                .map(r -> new RazaResource(r.getId(), r.getNombre(), r.getEspecieId()))
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PostMapping
    public ResponseEntity<RazaResource> create(@RequestBody @Valid RazaResource resource) {
        Raza raza = new Raza(resource.nombre(), resource.especieId());
        Raza saved = razaRepository.save(raza);
        return ResponseEntity.status(201).body(new RazaResource(saved.getId(), saved.getNombre(), saved.getEspecieId()));
    }
}
