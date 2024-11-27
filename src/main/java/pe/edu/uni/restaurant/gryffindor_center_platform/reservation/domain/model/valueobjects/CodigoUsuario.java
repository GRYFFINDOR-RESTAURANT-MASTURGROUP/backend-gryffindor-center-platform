package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects;

import java.util.UUID;

public record CodigoUsuario(UUID userCode) {
    public CodigoUsuario {
        if (userCode == null) {
            throw new IllegalArgumentException("El codigo del usuario cannot be null");
        }
    }
}
