package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects;

public record NombreCompletoUsuario(String nombreCompletoUsuario) {
    public NombreCompletoUsuario {
        if (nombreCompletoUsuario == null) {
            throw new IllegalArgumentException("Nombre completo del usuario cannot be null");
        }
    }
}
