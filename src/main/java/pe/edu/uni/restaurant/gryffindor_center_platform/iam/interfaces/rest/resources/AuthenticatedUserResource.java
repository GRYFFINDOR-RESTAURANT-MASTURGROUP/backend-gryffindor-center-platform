package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

/**
 * Recurso de usuario autenticado.
 * <p>
 *     Este recurso representa la información básica de un usuario autenticado,
 *     incluyendo su id, nombre de usuario y el token JWT.
 * </p>
 */
public record AuthenticatedUserResource(Long id, String userName, String token) {
}
