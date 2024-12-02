package pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Esta interfaz es responsable de proporcionar las operaciones relacionadas con la entidad TestingUser.
 * Extiende la interfaz JpaRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Este método es responsable de encontrar al usuario por su id.
   * @param id El id del usuario.
   * @return El objeto usuario.
   */
  Optional<User> findUserById(long id);

  /**
   * Este es un método predeterminado que podría implementarse para buscar por nombre de usuario,
   * pero en este caso no está implementado.
   */
  default List<User> findByUserNameFromUser(String userName) {
    return null;
  }

  /**
   * Este método es responsable de encontrar al usuario por su nombre de usuario.
   * @param userName El nombre de usuario.
   * @return El objeto usuario.
   */
  Optional<User> findByUserName(String userName);

  /**
   * Este método es responsable de verificar si el usuario existe por nombre de usuario.
   * @param userName El nombre de usuario.
   * @return Verdadero si el usuario existe, falso en caso contrario.
   */
  boolean existsByUserName(String userName);
}
