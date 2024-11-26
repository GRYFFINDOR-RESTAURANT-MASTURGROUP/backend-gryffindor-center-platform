package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.rest.resources;

import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.valueobjects.Status;

import java.util.UUID;

public record UserResource(Long id,
                           UUID userCode,
                           String firstName,
                           String lastName,
                           Status role) {
}
