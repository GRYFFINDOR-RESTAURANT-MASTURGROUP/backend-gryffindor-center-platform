package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.UserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

/**
 * Esta clase es un controlador REST que expone el recurso de usuarios.
 * Incluye las siguientes operaciones:
 * - GET /api/v1/usuarios: devuelve todos los usuarios
 * - GET /api/v1/usuarios/{userId}: devuelve el usuario con el id proporcionado
 **/
@RestController
@RequestMapping(value = "/api/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Usuarios", description = "Endpoint de gestión de usuarios")
public class UsersController {

  private final UserQueryService userQueryService;

  public UsersController(UserQueryService userQueryService) {
    this.userQueryService = userQueryService;
  }

  /**
   * Este método devuelve todos los usuarios.
   *
   * @return una lista de recursos de usuarios.
   * @see UserResource
   */
  @GetMapping
  public ResponseEntity<List<UserResource>> getAllUsers() {
    var getAllUsersQuery = new GetAllUsersQuery();
    var users = userQueryService.handle(getAllUsersQuery);
    var userResources = users.stream()
            .map(UserResourceFromEntityAssembler::toResourceFromEntity)
            .toList();
    return ResponseEntity.ok(userResources);
  }

  /**
   * Este método devuelve el usuario con el id proporcionado.
   *
   * @param userId el id del usuario.
   * @return el recurso de usuario con el id proporcionado.
   * @throws RuntimeException si no se encuentra el usuario.
   * @see UserResource
   */
  @GetMapping(value = "/{userId}")
  public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var user = userQueryService.handle(getUserByIdQuery);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return ResponseEntity.ok(userResource);
  }
}
