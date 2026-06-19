package org.example.vetalisbackend.iam.interfaces.rest;

import jakarta.validation.Valid;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.domain.model.commands.SignInCommand;
import org.example.vetalisbackend.iam.domain.model.commands.SignUpCommand;
import org.example.vetalisbackend.iam.application.commandservices.UserCommandService;
import org.example.vetalisbackend.iam.interfaces.rest.resources.AuthenticatedUserResource;
import org.example.vetalisbackend.iam.interfaces.rest.resources.SignInResource;
import org.example.vetalisbackend.iam.interfaces.rest.resources.SignUpResource;
import org.example.vetalisbackend.iam.interfaces.rest.resources.UserResource;
import org.example.vetalisbackend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import org.example.vetalisbackend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import org.example.vetalisbackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserCommandService userCommandService;

    public AuthController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserResource> signUp(@RequestBody @Valid SignUpResource resource) {
        SignUpCommand command = SignUpCommandFromResourceAssembler.toCommand(resource);
        Optional<User> user = userCommandService.handle(command);
        return user.map(u -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(UserResourceFromEntityAssembler.fromDomainModel(u)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody @Valid SignInResource resource) {
        SignInCommand command = SignInCommandFromResourceAssembler.toCommand(resource);
        Optional<String[]> result = userCommandService.handle(command);
        return result.map(arr -> {
            String token = arr[0];
            String role = arr[1];
            Long id = Long.parseLong(arr[2]);
            return ResponseEntity.ok(new AuthenticatedUserResource(id, resource.username(), token, role));
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
