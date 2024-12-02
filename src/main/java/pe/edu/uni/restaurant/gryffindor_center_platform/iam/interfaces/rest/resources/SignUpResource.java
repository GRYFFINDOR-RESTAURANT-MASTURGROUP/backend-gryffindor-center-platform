package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Recurso para el registro de usuario.
 * <p>
 *     Este recurso contiene la información necesaria para registrar un nuevo usuario,
 *     incluyendo el nombre de usuario, la contraseña y los roles asignados al usuario.
 * </p>
 */
public record SignUpResource(String userName, String password, List<String> roles) {
}
