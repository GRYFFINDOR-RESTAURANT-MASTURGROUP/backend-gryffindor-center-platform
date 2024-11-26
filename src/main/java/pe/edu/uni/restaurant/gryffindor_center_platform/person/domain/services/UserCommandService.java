package pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.commands.CreateUserCommand;

@Service
public interface UserCommandService {
    Long handle(CreateUserCommand command);
}
