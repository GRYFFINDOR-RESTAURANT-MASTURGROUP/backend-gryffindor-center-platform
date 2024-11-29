package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources;

import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.valueobjects.Role;

import java.util.UUID;

public record CreateTestingUserResource(
        UUID userCode,
        String nombreCompleto,
        Role role,
        String email) {
}
