package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.hashing.HashingService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.hashing.bcrypt.services.HashingServiceImpl;

/**
 * Esta interfaz es una interfaz de marcador para el servicio de hashing BCrypt.
 * Extiende las interfaces {@link HashingService} y {@link PasswordEncoder}.
 * Esta interfaz se utiliza para inyectar el servicio de hashing BCrypt en la clase {@link HashingServiceImpl}.
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
