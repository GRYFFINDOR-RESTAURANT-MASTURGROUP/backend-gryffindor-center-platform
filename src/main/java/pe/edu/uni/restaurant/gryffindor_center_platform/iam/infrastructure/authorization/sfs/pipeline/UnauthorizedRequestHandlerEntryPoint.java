package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.pipeline;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Manejador de solicitudes no autorizadas.
 * <p>
 * Esta clase es responsable de manejar las solicitudes no autorizadas.
 * Es utilizada por el framework de Spring Security para manejar solicitudes no autorizadas.
 * Implementa la interfaz AuthenticationEntryPoint.
 * </p>
 * @see AuthenticationEntryPoint
 */

@Component
public class UnauthorizedRequestHandlerEntryPoint implements AuthenticationEntryPoint {

  private static final Logger LOGGER
          = LoggerFactory.getLogger(UnauthorizedRequestHandlerEntryPoint.class);

  /**
   * Este método es llamado por el framework de Spring Security cuando se detecta una solicitud no autorizada.
   * @param request La solicitud que causó la excepción.
   * @param response La respuesta que será enviada al cliente.
   * @param authenticationException La excepción que causó la invocación.
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authenticationException) throws IOException, ServletException {

    LOGGER.error("Solicitud no autorizada: {}", authenticationException.getMessage());
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Solicitud no autorizada detectada");
  }
}
