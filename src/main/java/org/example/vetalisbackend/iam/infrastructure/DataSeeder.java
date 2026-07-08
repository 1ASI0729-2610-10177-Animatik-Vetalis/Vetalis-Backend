package org.example.vetalisbackend.iam.infrastructure;

import org.example.vetalisbackend.iam.application.internal.outboundservices.HashingService;
import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.domain.model.valueobjects.Role;
import org.example.vetalisbackend.iam.domain.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Crea los usuarios demo al arrancar si no existen en la BD.
 * Permite hacer login en el frontend con las cuentas de prueba.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final HashingService hashingService;

    public DataSeeder(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    @Override
    public void run(String... args) {
        seedUser("admin@vetalis.com",        "Admin123",    "Administrador Vetalis", Role.ADMIN);
        seedUser("test.vetalis@vetalis.com", "Vetalis123",  "Dr. Carlos Méndez",    Role.VETERINARIAN);
    }

    private void seedUser(String email, String password, String displayName, Role role) {
        String hash = hashingService.encode(password);
        if (userRepository.existsByUsername(email)) {
            userRepository.updateCredentials(email, hash, role);
            System.out.println("[DataSeeder] Credenciales actualizadas: " + email + " (" + role + ")");
        } else {
            User user = new User(email, hash, displayName, null, null, "Medicina General", role);
            userRepository.save(user);
            System.out.println("[DataSeeder] Usuario creado: " + email + " (" + role + ")");
        }
    }
}
