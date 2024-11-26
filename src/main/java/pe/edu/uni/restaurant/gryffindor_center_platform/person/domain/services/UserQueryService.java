package pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.queries.GetUserByUUIDQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetUserByUUIDQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
