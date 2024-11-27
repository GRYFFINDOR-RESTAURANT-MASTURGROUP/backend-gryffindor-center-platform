package pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateUserCommand;

@Service
public interface UserCommandService {
    Long handle(CreateUserCommand command);
}
