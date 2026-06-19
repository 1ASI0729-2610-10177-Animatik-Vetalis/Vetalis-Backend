package org.example.vetalisbackend.iam.interfaces.rest.transform;

import org.example.vetalisbackend.iam.domain.model.commands.SignInCommand;
import org.example.vetalisbackend.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommand(SignInResource resource) {
        return new SignInCommand(resource.username(), resource.password());
    }
}
