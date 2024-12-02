package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignUpCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByUserNameQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.UserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * IamContextFacade
 * <p>
 *     Esta clase es una fachada para el contexto IAM. Proporciona una interfaz simple para que otros
 *     contextos limitados interactúen con el contexto IAM.
 *     Esta clase es parte de la capa ACL.
 * </p>
 *
 */
@Service
public class IamContextFacade {
  private final UserCommandService userCommandService;
  private final UserQueryService userQueryService;

  public IamContextFacade(UserCommandService userCommandService,
                          UserQueryService userQueryService) {
    this.userCommandService = userCommandService;
    this.userQueryService = userQueryService;
  }

  /**
   * Crea un usuario con el nombre de usuario y la contraseña dados.
   * @param userName El nombre de usuario del usuario.
   * @param password La contraseña del usuario.
   * @return El id del usuario creado.
   */
  public Long createUser(String userName, String password) {
    var signUpCommand = new SignUpCommand(userName, password, List.of(Role.getDefaultRole()));
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty()) return 0L;
    return result.get().getId();
  }

  /**
   * Crea un usuario con el nombre de usuario, la contraseña y los roles dados.
   * @param userName El nombre de usuario del usuario.
   * @param password La contraseña del usuario.
   * @param roleNames Los nombres de los roles del usuario. Cuando un rol no existe,
   *                  se ignora.
   * @return El id del usuario creado.
   */
  public Long createUser(String userName, String password, List<String> roleNames) {
    var roles = roleNames != null
            ? roleNames.stream().map(Role::toRoleFromName).toList()
            : new ArrayList<Role>();
    var signUpCommand = new SignUpCommand(userName, password, roles);
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Obtiene el id del usuario con el nombre de usuario dado.
   * @param userName El nombre de usuario del usuario.
   * @return El id del usuario.
   */
  public Long fetchUserIdByUsername(String userName) {
    var getUserByUsernameQuery = new GetUserByUserNameQuery(userName);
    var result = userQueryService.handle(getUserByUsernameQuery);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Obtiene el nombre de usuario del usuario con el id dado.
   * @param userId El id del usuario.
   * @return El nombre de usuario del usuario.
   */
  public String fetchUsernameByUserId(Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty())
      return Strings.EMPTY;
    return result.get().getUserName();
  }

  /**
   * Obtiene un recurso de usuario a partir del id del usuario.
   * @param userId El id del usuario.
   * @return Un Optional con el recurso de usuario.
   */
  public Optional<UserResource> fetchUserById(Long userId){
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty())
      return Optional.empty();
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(result.get());
    return Optional.of(userResource);
  }
}
