package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.AuthenticatedUserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.SignInResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.SignUpResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.UserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

/**
 * AuthenticationController
 * <p>
 *     Este controlador es responsable de manejar las solicitudes de autenticación.
 *     Expone dos puntos finales:
 *     <ul>
 *         <li>POST /api/v1/auth/iniciar-sesion</li>
 *         <li>POST /api/v1/auth/registrarse</li>
 *     </ul>
 * </p>
 */
@RestController
@RequestMapping(value = "/api/v1/autenticacion", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Autenticacion", description = "Endpoint de autenticación")
public class AuthenticationController {

  private final UserCommandService userCommandService;

  public AuthenticationController(UserCommandService userCommandService) {
    this.userCommandService = userCommandService;
  }

  /**
   * Maneja la solicitud de inicio de sesión.
   * @param signInResource el cuerpo de la solicitud de inicio de sesión.
   * @return el recurso del usuario autenticado.
   */
  @PostMapping("/iniciar-sesion")
  public ResponseEntity<AuthenticatedUserResource> signIn(
          @RequestBody SignInResource signInResource) {

    var signInCommand = SignInCommandFromResourceAssembler
            .toCommandFromResource(signInResource);
    var authenticatedUser = userCommandService.handle(signInCommand);
    if (authenticatedUser.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    var authenticatedUserResource = AuthenticatedUserResourceFromEntityAssembler
            .toResourceFromEntity(
                    authenticatedUser.get().getLeft(), authenticatedUser.get().getRight());
    return ResponseEntity.ok(authenticatedUserResource);
  }

  /**
   * Maneja la solicitud de registro de usuario.
   * @param signUpResource el cuerpo de la solicitud de registro de usuario.
   * @return el recurso del usuario creado.
   */
  @PostMapping("/registrarse")
  public ResponseEntity<UserResource> signUp(@RequestBody SignUpResource signUpResource) {
    var signUpCommand = SignUpCommandFromResourceAssembler
            .toCommandFromResource(signUpResource);
    var user = userCommandService.handle(signUpCommand);
    if (user.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }
    var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
    return new ResponseEntity<>(userResource, HttpStatus.CREATED);
  }
}
