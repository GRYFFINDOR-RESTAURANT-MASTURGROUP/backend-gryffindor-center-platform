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
 * Esta clase representa la entidad User.
 *
 * @see AuditableAbstractAggregateRoot
 */
@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

  /**
   * Nombre de usuario
   */
  @NotBlank
  @Size(max = 50)
  @Column(unique = true)
  private String userName;

  /**
   * Contraseña del usuario
   */
  @NotBlank
  @Size(max = 120)
  private String password;

  /**
   * Roles asignados al usuario.
   * Relación muchos a muchos con la entidad Role.
   */
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(	name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  /**
   * Constructor por defecto.
   * Inicializa el conjunto de roles.
   */
  public User() {
    this.roles = new HashSet<>();
  }

  /**
   * Constructor con parámetros básicos.
   *
   * @param userName El nombre de usuario.
   * @param password La contraseña del usuario.
   */
  public User(String userName, String password) {
    this.userName = userName;
    this.password = password;
    this.roles = new HashSet<>();
  }

  /**
   * Constructor con roles.
   *
   * @param userName El nombre de usuario.
   * @param password La contraseña del usuario.
   * @param roles Lista de roles asignados al usuario.
   */
  public User(String userName, String password, List<Role> roles) {
    this(userName, password);
    addRoles(roles);
  }

  /**
   * Agregar un rol al usuario.
   *
   * @param role El rol a agregar.
   * @return El usuario con el rol añadido.
   */
  public User addRole(Role role) {
    this.roles.add(role);
    return this;
  }

  /**
   * Agregar una lista de roles al usuario.
   *
   * @param roles La lista de roles a agregar.
   * @return El usuario con los roles añadidos.
   */
  public User addRoles(List<Role> roles) {
    var validatedRoleSet = Role.validateRoleSet(roles);
    this.roles.addAll(validatedRoleSet);
    return this;
  }
}