package pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.queries.GetTestingUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.queries.GetTestingUserByUUIDQuery;

import java.util.List;
import java.util.Optional;

public interface TestingUserQueryService {
    List<TestingUser> handle(GetTestingUserByUUIDQuery query);
    Optional<TestingUser> handle(GetTestingUserByIdQuery query);
}
