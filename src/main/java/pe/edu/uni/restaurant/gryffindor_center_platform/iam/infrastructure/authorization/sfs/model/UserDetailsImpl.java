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
 * This class is responsible for providing the user details to the Spring Security framework.
 * It implements the UserDetails interface.
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

  private final String username;
  /*private final String dni;*/
  @JsonIgnore
  private final String password;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(String username, /*String dni,*/ String password,
      Collection<? extends GrantedAuthority> authorities) {
    this.username = username;
    /*this.dni = dni;*/
    this.password = password;
    this.authorities = authorities;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
  }

  /**
   * This method is responsible for building the UserDetailsImpl object from the TestingUser object.
   * @param user The user object.
   * @return The UserDetailsImpl object.
   */
  public static UserDetailsImpl build(User user) {
    var authorities = user.getRoles().stream()
        .map(role -> role.getName().name())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    return new UserDetailsImpl(
            user.getUserName(),
            /*user.getDni(),*/
            user.getPassword(),
            authorities);
  }
}
