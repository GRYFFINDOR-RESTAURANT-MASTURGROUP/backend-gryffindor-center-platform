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
 * Implementation of {@link UserQueryService} interface.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
  private final UserRepository userRepository;

  /**
   * Constructor.
   *
   * @param userRepository {@link UserRepository} instance.
   */
  public UserQueryServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * This method is used to handle {@link GetAllUsersQuery} query.
   * @param query {@link GetAllUsersQuery} instance.
   * @return {@link List} of {@link User} instances.
   * @see GetAllUsersQuery
   */
  @Override
  public List<User> handle(GetAllUsersQuery query) {
    return userRepository.findAll();
  }

  /**
   * This method is used to handle {@link GetUserByIdQuery} query.
   * @param query {@link GetUserByIdQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByIdQuery
   */
  @Override
  public Optional<User> handle(GetUserByIdQuery query) {
    return userRepository.findById(query.userId());
  }

  /**
   * This method is used to handle {@link GetUserByUserNameQuery} query.
   * @param query {@link GetUserByUserNameQuery} instance.
   * @return {@link Optional} of {@link User} instance.
   * @see GetUserByUserNameQuery
   */
  @Override
  public Optional<User> handle(GetUserByUserNameQuery query) {
    return userRepository.findByUserName(query.userName());
  }
}
