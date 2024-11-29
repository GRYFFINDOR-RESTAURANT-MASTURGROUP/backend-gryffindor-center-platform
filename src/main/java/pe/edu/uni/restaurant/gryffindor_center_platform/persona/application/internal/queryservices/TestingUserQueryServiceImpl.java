package pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.queries.GetTestingUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.queries.GetTestingUserByUUIDQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services.TestingUserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories.TestingUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestingUserQueryServiceImpl implements TestingUserQueryService {
    private final TestingUserRepository testingUserRepository;

    public TestingUserQueryServiceImpl(TestingUserRepository testingUserRepository) {
        this.testingUserRepository = testingUserRepository;
    }

    @Override
    public List<TestingUser> handle(GetTestingUserByUUIDQuery query) {
        return this.testingUserRepository.findByUserCodeUser(query.userCode());
    }

    @Override
    public Optional<TestingUser> handle(GetTestingUserByIdQuery query) {
        return this.testingUserRepository.findById(query.id());
    }
}
