package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByUserNameQuery;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz UserQueryService.
 * <p>
 *     Esta interfaz define los métodos para manejar las consultas relacionadas con los usuarios,
 *     como obtener todos los usuarios, obtener un usuario por ID y obtener un usuario por nombre de usuario.
 * </p>
 */
public interface UserQueryService {

  /**
   * Maneja la consulta para obtener todos los usuarios.
   * <p>
   *     Este método devuelve una lista de todos los usuarios registrados en el sistema.
   * </p>
   * @param query la consulta para obtener todos los usuarios (GetAllUsersQuery).
   * @return una lista de usuarios.
   */
  List<User> handle(GetAllUsersQuery query);

  /**
   * Maneja la consulta para obtener un usuario por su ID.
   * <p>
   *     Este método devuelve un `Optional` que contiene el usuario encontrado o vacío si no se encuentra.
   * </p>
   * @param query la consulta para obtener un usuario por ID (GetUserByIdQuery).
   * @return un `Optional` que contiene el usuario correspondiente o vacío si no se encuentra.
   */
  Optional<User> handle(GetUserByIdQuery query);

  /**
   * Maneja la consulta para obtener un usuario por su nombre de usuario.
   * <p>
   *     Este método devuelve un `Optional` que contiene el usuario encontrado o vacío si no se encuentra.
   * </p>
   * @param query la consulta para obtener un usuario por nombre de usuario (GetUserByUserNameQuery).
   * @return un `Optional` que contiene el usuario correspondiente o vacío si no se encuentra.
   */
  Optional<User> handle(GetUserByUserNameQuery query);
}
