package pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateTestingUserCommand;

@Service
public interface TestingUserCommandService {
    Long handle(CreateTestingUserCommand command);
}
