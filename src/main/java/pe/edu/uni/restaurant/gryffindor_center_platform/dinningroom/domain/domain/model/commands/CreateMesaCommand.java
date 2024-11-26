package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.valueobjects.Status;

import java.util.UUID;

public record CreateMesaCommand(
        UUID userCode,
        String firstName,
        String lastName,
        Status role
) {
}
