package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

/**
 * Recurso para inicio de sesión.
 * <p>
 *     Este recurso contiene las credenciales necesarias para el inicio de sesión,
 *     incluyendo el nombre de usuario y la contraseña.
 * </p>
 */
public record SignInResource(String userName, String password) {
}
