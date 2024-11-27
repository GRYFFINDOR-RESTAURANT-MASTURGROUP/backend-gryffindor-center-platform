package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects;

public record CorreoUsuario(String correoUsuario) {
    public CorreoUsuario {
        if (correoUsuario == null) {
            throw new IllegalArgumentException("Correo del usuario cannot be null");
        }
    }
}
