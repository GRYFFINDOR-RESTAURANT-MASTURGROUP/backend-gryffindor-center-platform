package pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories.UserRepository;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByUserCode(command.userCode())) {
            throw new IllegalArgumentException("User with this user code already exists");
        }
        var inventoryItem = new User(command);
        try {
            this.userRepository.save(inventoryItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return inventoryItem.getId();
    }
}

