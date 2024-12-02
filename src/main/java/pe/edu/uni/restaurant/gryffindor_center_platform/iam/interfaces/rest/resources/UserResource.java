package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

import java.util.List;

/**
 * Recurso de usuario.
 * <p>
 *     Este recurso contiene la información de un usuario, incluyendo su id,
 *     nombre de usuario y los roles asignados al usuario.
 * </p>
 */
public record UserResource(Long id, String userName, List<String> roles) {

}
