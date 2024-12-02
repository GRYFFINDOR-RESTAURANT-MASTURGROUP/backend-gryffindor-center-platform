package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.tokens.jwt.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.tokens.jwt.BearerTokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * Implementación del servicio de tokens para JWT.
 * Esta clase es responsable de generar y validar los tokens JWT.
 * Utiliza el secreto y los días de expiración desde el archivo application.properties.
 */
@Service
public class TokenServiceImpl implements BearerTokenService {
  private final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

  private static final String AUTHORIZATION_PARAMETER_NAME = "Authorization";
  private static final String BEARER_TOKEN_PREFIX = "Bearer ";

  private static final int TOKEN_BEGIN_INDEX = 7;

  @Value("${authorization.jwt.secret}")
  private String secret;

  @Value("${authorization.jwt.expiration.days}")
  private int expirationDays;

  /**
   * Este método genera un token JWT a partir de un objeto de autenticación.
   * @param authentication el objeto de autenticación
   * @return String el token JWT
   * @see Authentication
   */
  @Override
  public String generateToken(Authentication authentication) {
    return buildTokenWithDefaultParameters(authentication.getName());
  }

  /**
   * Este método genera un token JWT a partir de un nombre de usuario.
   * @param username el nombre de usuario
   * @return String el token JWT
   */
  public String generateToken(String username) {
    return buildTokenWithDefaultParameters(username);
  }

  /**
   * Este método genera un token JWT a partir de un nombre de usuario y un secreto.
   * Utiliza los días de expiración predeterminados desde el archivo application.properties.
   * @param username el nombre de usuario
   * @return String el token JWT
   */
  private String buildTokenWithDefaultParameters(String username) {
    var issuedAt = new Date();
    var expiration = DateUtils.addDays(issuedAt, expirationDays);
    var key = getSigningKey();
    return Jwts.builder()
            .subject(username)
            .issuedAt(issuedAt)
            .expiration(expiration)
            .signWith(key)
            .compact();
  }

  /**
   * Este método extrae el nombre de usuario de un token JWT.
   * @param token el token
   * @return String el nombre de usuario
   */
  @Override
  public String getUserNameFromToken(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  /**
   * Este método valida un token JWT.
   * @param token el token
   * @return boolean verdadero si el token es válido, falso en caso contrario
   */
  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
      LOGGER.info("El token es válido");
      return true;
    }  catch (SignatureException e) {
      LOGGER.error("Firma de token JSON Web inválida: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      LOGGER.error("Token JSON Web inválido: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      LOGGER.error("El token JSON Web está expirado: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      LOGGER.error("El token JSON Web no es compatible: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      LOGGER.error("La cadena de claims del token JSON Web está vacía: {}", e.getMessage());
    }
    return false;
  }

  /**
   * Extrae un claim de un token.
   * @param token el token
   * @param claimsResolvers el resolutor de claims
   * @param <T> el tipo del claim
   * @return T el claim
   */
  private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }

  /**
   * Extrae todos los claims de un token.
   * @param token el token
   * @return Claims los claims
   */
  private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  /**
   * Obtiene la clave de firma.
   * @return SecretKey la clave de firma
   */
  private SecretKey getSigningKey() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private boolean isTokenPresentIn(String authorizationParameter) {
    return StringUtils.hasText(authorizationParameter);
  }

  private boolean isBearerTokenIn(String authorizationParameter) {
    return authorizationParameter.startsWith(BEARER_TOKEN_PREFIX);
  }

  private String extractTokenFrom(String authorizationHeaderParameter) {
    return authorizationHeaderParameter.substring(TOKEN_BEGIN_INDEX);
  }

  private String getAuthorizationParameterFrom(HttpServletRequest request) {
    return request.getHeader(AUTHORIZATION_PARAMETER_NAME);
  }

  @Override
  public String getBearerTokenFrom(HttpServletRequest request) {
    String parameter = getAuthorizationParameterFrom(request);
    if (isTokenPresentIn(parameter) && isBearerTokenIn(parameter))
      return extractTokenFrom(parameter);
    return null;
  }
}
