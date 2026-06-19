package org.example.vetalisbackend.iam.interfaces.rest.transform;

import org.example.vetalisbackend.iam.domain.model.commands.SignUpCommand;
import org.example.vetalisbackend.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommand(SignUpResource resource) {
        return new SignUpCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.password(),
                resource.dni(),
                resource.telefono(),
                resource.especialidad()
        );
    }
}
