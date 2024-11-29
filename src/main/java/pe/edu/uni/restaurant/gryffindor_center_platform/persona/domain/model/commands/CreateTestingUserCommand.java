package pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.valueobjects.Role;

import java.util.UUID;

public record CreateTestingUserCommand(
        UUID userCode,
        String nombreCompleto,
        Role role,
        String email
) {
}
