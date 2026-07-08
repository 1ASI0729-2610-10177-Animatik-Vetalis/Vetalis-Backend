package org.example.vetalisbackend.iam.interfaces.rest;

import org.example.vetalisbackend.iam.domain.model.valueobjects.Role;
import org.example.vetalisbackend.iam.domain.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/veterinarios")
@CrossOrigin(origins = "*")
public class VeterinarioController {

    private final UserRepository userRepository;

    public VeterinarioController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        List<Map<String, Object>> result = userRepository.findByRole(Role.VETERINARIAN).stream()
                .map(u -> Map.<String, Object>of(
                        "id", u.getId(),
                        "nombre", u.getDisplayName() != null ? u.getDisplayName() : u.getUsername(),
                        "email", u.getUsername(),
                        "especialidad", u.getEspecialidad() != null ? u.getEspecialidad() : "General"
                ))
                .toList();
        return ResponseEntity.ok(result);
    }
}
