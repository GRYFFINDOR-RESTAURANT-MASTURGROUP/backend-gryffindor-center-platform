package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.valueobjects.Roles;

import java.util.List;

/**
 * Entidad Role para definir un rol o más.
 * <p>
 *     Esta entidad representa el rol de un usuario en el sistema.
 *     Se utiliza para definir los permisos de un usuario.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class Role {
  /**
   * Identificador único del rol.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * Nombre del rol, representado como un valor de un enum {@link Roles}.
   */
  @Enumerated(EnumType.STRING)
  @Column(length = 20)
  private Roles name;

  /**
   * Constructor de Role con nombre de rol.
   * @param name el nombre del rol.
   */
  public Role(Roles name) {
    this.name = name;
  }

  /**
   * Obtener el nombre del rol como cadena de caracteres.
   * @return el nombre del rol como una cadena de caracteres.
   */
  public String getStringName() {
    return name.name();
  }

  /**
   * Obtener el rol predeterminado (ROLE_USER).
   * @return el rol predeterminado.
   */
  public static Role getDefaultRole() {
    return new Role(Roles.ROLE_USER);
  }

  /**
   * Obtener el rol a partir de su nombre.
   * @param name el nombre del rol.
   * @return el rol correspondiente.
   */
  public static Role toRoleFromName(String name) {
    return new Role(Roles.valueOf(name));
  }

  /**
   * Validar un conjunto de roles.
   * <p>
   *     Este método valida el conjunto de roles y devuelve el rol predeterminado si el conjunto está vacío.
   * </p>
   * @param roles el conjunto de roles.
   * @return el conjunto de roles validado.
   */
  public static List<Role> validateRoleSet(List<Role> roles) {
    if (roles == null || roles.isEmpty()) {
      return List.of(getDefaultRole());
    }
    return roles;
  }
}
