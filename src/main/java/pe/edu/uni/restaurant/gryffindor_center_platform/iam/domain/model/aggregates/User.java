package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TestingUser aggregate root
 * This class represents the aggregate root for the TestingUser entity.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

  /*@OneToOne(mappedBy ="user",cascade = CascadeType.ALL)
  private Profile profile;*/

  /**
   * Nombre de usuario
   */
  @NotBlank
  @Size(max = 50)
  @Column(unique = true)
  private String userName;

  /*
  @NotBlank
  @Size(min = 8, max = 9)
  @Column(unique = true)
  private String dni;*/

  /**
   * Contraseña del usuario
   */
  @NotBlank
  @Size(max = 120)
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(	name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  public User() {
    this.roles = new HashSet<>();
  }
  public User(String userName, /*String dni,*/ String password) {
    this.userName = userName;
    /*this.dni = dni;*/
    this.password = password;
    this.roles = new HashSet<>();
  }

  public User(String userName, /*String dni,*/ String password, List<Role> roles) {
    this(userName, /*dni,*/ password);
    addRoles(roles);
  }

  /**
   * Add a role to the user
   * @param role the role to add
   * @return the user with the added role
   */
  public User addRole(Role role) {
    this.roles.add(role);
    return this;
  }

  /**
   * Add a list of roles to the user
   * @param roles the list of roles to add
   * @return the user with the added roles
   */
  public User addRoles(List<Role> roles) {
    var validatedRoleSet = Role.validateRoleSet(roles);
    this.roles.addAll(validatedRoleSet);
    return this;
  }
}