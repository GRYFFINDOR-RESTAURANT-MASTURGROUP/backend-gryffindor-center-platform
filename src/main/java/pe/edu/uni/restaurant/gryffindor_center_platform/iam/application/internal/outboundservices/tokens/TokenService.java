package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.tokens;

/**
 * Interfaz TokenService
 * Esta interfaz se utiliza para generar y validar tokens
 */
public interface TokenService {

  /**
   * Genera un token para un nombre de usuario dado
   * @param userName el nombre de usuario
   * @return String el token generado
   */
  String generateToken(String userName);

  /**
   * Extrae el nombre de usuario de un token
   * @param token el token
   * @return String el nombre de usuario
   */
  String getUserNameFromToken(String token);

  /**
   * Valida un token
   * @param token el token
   * @return boolean true si el token es válido, false en caso contrario
   */
  boolean validateToken(String token);
}
