package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class MesaContextFacade {
    private final UserRepository userRepository;

    public MesaContextFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<UUID> getUserCodeUser(UUID userCode) {
        return userRepository.findByUserCode(userCode)
                .map(Mesa::getUserCode);
    }
}
