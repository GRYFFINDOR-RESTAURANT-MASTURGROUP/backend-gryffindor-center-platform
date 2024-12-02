package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.tokens.TokenService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.tokens.jwt.services.TokenServiceImpl;

/**
 * Esta interfaz es una interfaz marcador para el servicio de tokens JWT.
 * Extiende la interfaz {@link TokenService}.
 * Esta interfaz se utiliza para inyectar el servicio de tokens JWT en la clase {@link TokenServiceImpl}.
 */
public interface BearerTokenService extends TokenService {

  /**
   * Este método es responsable de extraer el token JWT de la solicitud HTTP.
   * @param token la solicitud HTTP
   * @return String el token JWT
   */
  String getBearerTokenFrom(HttpServletRequest token);

  /**
   * Este método es responsable de generar un token JWT a partir de un objeto de autenticación.
   * @param authentication el objeto de autenticación
   * @return String el token JWT
   * @see Authentication
   */
  String generateToken(Authentication authentication);
}
