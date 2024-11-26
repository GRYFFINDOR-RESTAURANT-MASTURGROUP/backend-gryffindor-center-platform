package pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.valueobjects.Role;

import java.util.UUID;

public record CreateUserCommand(
        UUID userCode,
        String firstName,
        String lastName,
        Role role
) {
}
