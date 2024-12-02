package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignInCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignUpCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

/**
 * TestingUser command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

  private final UserRepository userRepository;
  private final HashingService hashingService;
  private final TokenService tokenService;

  private final RoleRepository roleRepository;

  public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService,
      TokenService tokenService, RoleRepository roleRepository) {

    this.userRepository = userRepository;
    this.hashingService = hashingService;
    this.tokenService = tokenService;
    this.roleRepository = roleRepository;
  }

  /**
   * Handle the sign-in command
   * <p>
   *     This method handles the {@link SignInCommand} command and returns the user and the token.
   * </p>
   * @param command the sign-in command containing the username and password
   * @return and optional containing the user matching the username and the generated token
   * @throws RuntimeException if the user is not found or the password is invalid
   */
  @Override
  public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
    var user = userRepository.findByUserName(command.userName());
    if (user.isEmpty())
      throw new RuntimeException("TestingUser not found");
    if (!hashingService.matches(command.password(), user.get().getPassword()))
      throw new RuntimeException("Invalid password");

    var token = tokenService.generateToken(user.get().getUserName());
    return Optional.of(ImmutablePair.of(user.get(), token));
  }

  /**
   * Implementación del servicio de comandos para usuarios
   * <p>
   *     Esta clase implementa la interfaz {@link UserCommandService} y proporciona la implementación
   *     para los comandos {@link SignInCommand} y {@link SignUpCommand}.
   * </p>
   */
  @Override
  public Optional<User> handle(SignUpCommand command) {
    if (userRepository.existsByUserName(command.userName()))
      throw new RuntimeException("Este nombre de usuario ya existe");
    var roles = command.roles().stream()
        .map(role ->
            roleRepository.findByName(role.getName())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado")))
        .toList();
    var user = new User(command.userName(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByUserName(command.userName());
  }
}
