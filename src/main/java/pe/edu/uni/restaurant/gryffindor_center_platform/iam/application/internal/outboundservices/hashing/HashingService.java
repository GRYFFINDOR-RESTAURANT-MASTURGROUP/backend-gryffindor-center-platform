package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.hashing;

/**
 * Interfaz HashingService
 * Esta interfaz se utiliza para codificar y comparar contraseñas
 */
public interface HashingService {
  /**
   * Codifica una contraseña
   * @param rawPassword la contraseña a codificar
   * @return String la contraseña codificada
   */
  String encode(CharSequence rawPassword);

  /**
   * Compara una contraseña sin codificar con una contraseña codificada
   * @param rawPassword la contraseña sin codificar
   * @param encodedPassword la contraseña codificada
   * @return boolean true si la contraseña sin codificar coincide con la codificada, false en caso contrario
   */
  boolean matches(CharSequence rawPassword, String encodedPassword);
}
