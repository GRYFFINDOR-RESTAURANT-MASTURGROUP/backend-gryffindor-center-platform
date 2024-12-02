package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

/**
 * Esta clase se utiliza para construir el objeto UsernamePasswordAuthenticationToken
 * que se usa para autenticar al usuario.
 */
public class UsernamePasswordAuthenticationTokenBuilder {

  /**
   * Este método es responsable de construir el objeto UsernamePasswordAuthenticationToken.
   * @param principal Los detalles del usuario.
   * @param request La solicitud HTTP.
   * @return El objeto UsernamePasswordAuthenticationToken.
   * @see UsernamePasswordAuthenticationToken
   * @see UserDetails
   */
  public static UsernamePasswordAuthenticationToken build(UserDetails principal,
                                                          HttpServletRequest request) {

    var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(principal,
            null, principal.getAuthorities());
    usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
    return usernamePasswordAuthenticationToken;
  }
}
