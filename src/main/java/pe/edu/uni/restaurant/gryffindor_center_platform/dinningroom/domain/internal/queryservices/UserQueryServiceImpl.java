package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.queries.GetMesaByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.queries.GetMesaByUUIDQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.services.MesaQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements MesaQueryService {
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Mesa> handle(GetMesaByUUIDQuery query) {
        return this.userRepository.findByUserCodeUser(query.userCode());
    }

    @Override
    public Optional<Mesa> handle(GetMesaByIdQuery query) {
        return this.userRepository.findById(query.id());
    }
}
