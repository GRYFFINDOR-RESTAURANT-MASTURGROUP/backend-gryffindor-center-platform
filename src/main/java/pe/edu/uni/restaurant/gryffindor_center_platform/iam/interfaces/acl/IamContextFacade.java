package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignUpCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByUserNameQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.UserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other
 *     bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
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
   * Creates a user with the given username and password.
   * @param userName The username of the user.
   * @param password The password of the user.
   * @return The id of the created user.
   */
  public Long createUser(String userName, /*String dni,*/ String password) {
    var signUpCommand = new SignUpCommand(userName, /*dni,*/ password, List.of(Role.getDefaultRole()));
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty()) return 0L;
    return result.get().getId();
  }

  /**
   * Creates a user with the given username, password and roles.
   * @param userName The username of the user.
   * @param password The password of the user.
   * @param roleNames The names of the roles of the user. When a role does not exist,
   *                  it is ignored.
   * @return The id of the created user.
   */
  public Long createUser(String userName, /*String dni,*/ String password, List<String> roleNames) {
    var roles = roleNames != null
        ? roleNames.stream().map(Role::toRoleFromName).toList()
        : new ArrayList<Role>();
    var signUpCommand = new SignUpCommand(userName, /*dni,*/ password, roles);
    var result = userCommandService.handle(signUpCommand);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Fetches the id of the user with the given username.
   * @param userName The username of the user.
   * @return The id of the user.
   */
  public Long fetchUserIdByUsername(String userName) {
    var getUserByUsernameQuery = new GetUserByUserNameQuery(userName);
    var result = userQueryService.handle(getUserByUsernameQuery);
    if (result.isEmpty())
      return 0L;
    return result.get().getId();
  }

  /**
   * Fetches the username of the user with the given id.
   * @param userId The id of the user.
   * @return The username of the user.
   */
  public String fetchUsernameByUserId(Long userId) {
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty())
      return Strings.EMPTY;
    return result.get().getUserName();
  }

  public Optional<UserResource> fetchUserById(Long userId){
    var getUserByIdQuery = new GetUserByIdQuery(userId);
    var result = userQueryService.handle(getUserByIdQuery);
    if (result.isEmpty())
      return Optional.empty();
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(result.get());
    return Optional.of(userResource);
  }
}
