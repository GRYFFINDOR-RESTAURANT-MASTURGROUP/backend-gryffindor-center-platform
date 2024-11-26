package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects;

import java.util.UUID;

public record UserCode(UUID userCode) {
    public UserCode {
        if (userCode == null) {
            throw new IllegalArgumentException("UserCode cannot be null");
        }
    }
}
