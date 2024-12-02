package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllUsersQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetUserByUserNameQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.UserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link UserQueryService}.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
  private final UserRepository userRepository;

  /**
   * Constructor.
   *
   * @param userRepository Instancia de {@link UserRepository}.
   */
  public UserQueryServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Este método se utiliza para manejar la consulta {@link GetAllUsersQuery}.
   * @param query Instancia de {@link GetAllUsersQuery}.
   * @return {@link List} de instancias de {@link User}.
   * @see GetAllUsersQuery
   */
  @Override
  public List<User> handle(GetAllUsersQuery query) {
    return userRepository.findAll();
  }

  /**
   * Este método se utiliza para manejar la consulta {@link GetUserByIdQuery}.
   * @param query Instancia de {@link GetUserByIdQuery}.
   * @return {@link Optional} de una instancia de {@link User}.
   * @see GetUserByIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByIdQuery query) {
    return userRepository.findById(query.userId());
  }

  /**
   * Este método se utiliza para manejar la consulta {@link GetUserByUserNameQuery}.
   * @param query Instancia de {@link GetUserByUserNameQuery}.
   * @return {@link Optional} de una instancia de {@link User}.
   * @see GetUserByUserNameQuery
   */
  @Override
  public Optional<User> handle(GetUserByUserNameQuery query) {
    return userRepository.findByUserName(query.userName());
  }
}
