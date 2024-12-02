package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

/**
 * Esta clase es responsable de proporcionar los detalles del usuario al framework de Spring Security.
 * Implementa la interfaz UserDetailsService.
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Este método es responsable de cargar los detalles del usuario desde la base de datos.
   * @param username El nombre de usuario.
   * @return El objeto UserDetails.
   * @throws UsernameNotFoundException Si el usuario no es encontrado.
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByUserName(username)
            .orElseThrow(
                    () -> new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username));
    return UserDetailsImpl.build(user);
  }
}
