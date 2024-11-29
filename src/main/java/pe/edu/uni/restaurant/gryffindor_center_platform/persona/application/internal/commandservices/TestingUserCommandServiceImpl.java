package pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateTestingUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services.TestingUserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories.TestingUserRepository;

@Service
public class TestingUserCommandServiceImpl implements TestingUserCommandService {
    private final TestingUserRepository testingUserRepository;

    public TestingUserCommandServiceImpl(TestingUserRepository testingUserRepository) {
        this.testingUserRepository = testingUserRepository;
    }

    @Override
    public Long handle(CreateTestingUserCommand command) {
        if (testingUserRepository.existsByUserCode(command.userCode())) {
            throw new IllegalArgumentException("TestingUser with this user code already exists");
        }
        var inventoryItem = new TestingUser(command);
        try {
            this.testingUserRepository.save(inventoryItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return inventoryItem.getId();
    }
}

