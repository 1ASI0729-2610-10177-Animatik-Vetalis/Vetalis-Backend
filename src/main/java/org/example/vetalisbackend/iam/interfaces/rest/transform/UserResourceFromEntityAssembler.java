package org.example.vetalisbackend.iam.interfaces.rest.transform;

import org.example.vetalisbackend.iam.domain.model.aggregates.User;
import org.example.vetalisbackend.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource fromDomainModel(User user) {
        return new UserResource(
                user.getId(),
                user.getUsername(),
                user.getDisplayName(),
                user.getDni(),
                user.getTelefono(),
                user.getEspecialidad(),
                user.getRole().name()
        );
    }
}
