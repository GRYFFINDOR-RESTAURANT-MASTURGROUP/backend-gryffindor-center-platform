package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories.TestingUserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TestingUserContextFacade {
    private final TestingUserRepository testingUserRepository;

    public TestingUserContextFacade(TestingUserRepository testingUserRepository) {
        this.testingUserRepository = testingUserRepository;
    }
    public Optional<UUID> getUserCodeUser(UUID userCode) {
        return testingUserRepository.findByUserCode(userCode)
                .map(TestingUser::getUserCode);
    }
}
