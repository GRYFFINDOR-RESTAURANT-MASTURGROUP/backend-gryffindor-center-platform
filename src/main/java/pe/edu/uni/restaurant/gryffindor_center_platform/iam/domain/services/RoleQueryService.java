package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz RoleQueryService.
 * <p>
 *     Esta interfaz define los métodos para manejar las consultas relacionadas con los roles en el sistema.
 *     Se utiliza para obtener todos los roles o un rol específico por su nombre.
 * </p>
 */
public interface RoleQueryService {

  /**
   * Maneja la consulta para obtener todos los roles del sistema.
   * @param query la consulta GetAllRolesQuery.
   * @return una lista de roles.
   */
  List<Role> handle(GetAllRolesQuery query);

  /**
   * Maneja la consulta para obtener un rol específico por su nombre.
   * @param query la consulta GetRoleByNameQuery.
   * @return un rol específico si se encuentra, o un valor vacío si no se encuentra.
   */
  Optional<Role> handle(GetRoleByNameQuery query);
}
