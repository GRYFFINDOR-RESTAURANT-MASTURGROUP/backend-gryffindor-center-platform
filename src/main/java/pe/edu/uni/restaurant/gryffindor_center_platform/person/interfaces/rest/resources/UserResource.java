package pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.resources;

import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.valueobjects.Role;

import java.util.UUID;

public record UserResource(Long id,
                           UUID userCode,
                           String firstName,
                           String lastName,
                           Role role) {
}
