package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignInCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

/**
 * Interfaz UserCommandService.
 * <p>
 *     Esta interfaz define los métodos para manejar los comandos relacionados con las acciones de
 *     iniciar sesión (sign in) y registrarse (sign up) de un usuario.
 *     Los métodos permiten realizar el inicio de sesión y el registro de usuarios, respectivamente.
 * </p>
 */
public interface UserCommandService {

  /**
   * Maneja el comando de iniciar sesión.
   * <p>
   *     Este método permite iniciar sesión en el sistema con las credenciales del usuario.
   *     Devuelve un Optional que contiene un par inmutable, donde el primer elemento es el objeto
   *     `User` y el segundo elemento es el token de autenticación (si se proporciona).
   * </p>
   * @param command el comando de inicio de sesión (SignInCommand).
   * @return un Optional que contiene el par inmutable con el usuario y el token de autenticación.
   */
  Optional<ImmutablePair<User, String>> handle(SignInCommand command);

  /**
   * Maneja el comando de registro de un nuevo usuario.
   * <p>
   *     Este método permite registrar un nuevo usuario en el sistema. Devuelve un Optional que
   *     contiene el usuario registrado.
   * </p>
   * @param command el comando de registro (SignUpCommand).
   * @return un Optional que contiene el usuario registrado.
   */
  Optional<User> handle(SignUpCommand command);
}