package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.valueobjects.Roles;

import java.util.Optional;

/**
 * Esta interfaz es responsable de proporcionar las operaciones relacionadas con la entidad Role.
 * Extiende la interfaz JpaRepository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
   * Este método es responsable de encontrar el rol por nombre.
   * @param name El nombre del rol.
   * @return El objeto rol.
   */
  Optional<Role> findByName(Roles name);

  /**
   * Este método es responsable de verificar si el rol existe por nombre.
   * @param name El nombre del rol.
   * @return Verdadero si el rol existe, falso de lo contrario.
   */
  boolean existsByName(Roles name);
}
