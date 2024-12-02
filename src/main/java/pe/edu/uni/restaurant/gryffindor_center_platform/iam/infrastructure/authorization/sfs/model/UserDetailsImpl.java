package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Esta clase es responsable de proporcionar los detalles del usuario al framework de Spring Security.
 * Implementa la interfaz UserDetails.
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private final String username;
  @JsonIgnore
  private final String password;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, String password,
                         Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

  /**
   * Este método es responsable de construir el objeto UserDetailsImpl a partir del objeto User.
   * @param user El objeto usuario.
   * @return El objeto UserDetailsImpl.
   */
  public static UserDetailsImpl build(User user) {
    var authorities = user.getRoles().stream()
            .map(role -> role.getName().name())
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UserDetailsImpl(
            user.getUserName(),
            user.getPassword(),
            authorities);
  }
}
