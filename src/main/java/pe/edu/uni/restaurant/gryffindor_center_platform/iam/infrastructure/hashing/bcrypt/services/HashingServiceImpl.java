package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.hashing.bcrypt.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

/**
 * Esta clase implementa la interfaz {@link BCryptHashingService}.
 * Se utiliza para hacer hash de contraseñas utilizando el algoritmo BCrypt.
 */
@Service
public class HashingServiceImpl implements BCryptHashingService {
  private final BCryptPasswordEncoder passwordEncoder;

  HashingServiceImpl() {
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  /**
   * Hace un hash de una contraseña utilizando el algoritmo BCrypt.
   * @param rawPassword la contraseña para hacer el hash
   * @return String la contraseña con hash
   */
  @Override
  public String encode(CharSequence rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  /**
   * Verifica si una contraseña sin procesar coincide con una contraseña con hash.
   * @param rawPassword la contraseña sin procesar
   * @param encodedPassword la contraseña con hash
   * @return boolean verdadero si la contraseña sin procesar coincide con la contraseña con hash, falso en caso contrario
   */
  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
