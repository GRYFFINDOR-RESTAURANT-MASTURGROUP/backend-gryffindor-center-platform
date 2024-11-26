package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.services.MesaCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.repositories.UserRepository;

@Service
public class UserCommandServiceImpl implements MesaCommandService {
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateMesaCommand command) {
        if (userRepository.existsByUserCode(command.userCode())) {
            throw new IllegalArgumentException("Mesa with this user code already exists");
        }
        var inventoryItem = new Mesa(command);
        try {
            this.userRepository.save(inventoryItem);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return inventoryItem.getId();
    }
}

