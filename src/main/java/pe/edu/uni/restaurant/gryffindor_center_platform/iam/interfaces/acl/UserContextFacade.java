package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserContextFacade {
    private final UserRepository userRepository;

    public UserContextFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<String> getUserNameFromUser(String userName) {
        return userRepository.findByUserName(userName)
                .map(User::getUserName);
    }
}
