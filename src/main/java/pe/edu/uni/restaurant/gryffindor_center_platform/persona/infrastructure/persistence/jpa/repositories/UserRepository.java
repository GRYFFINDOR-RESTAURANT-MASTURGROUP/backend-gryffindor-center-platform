package pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    default List<User> findByUserCodeUser(UUID userCodeUser) {
        return null;
    }

    Optional<User> findByUserCode(UUID userCode);
    boolean existsByUserCode(UUID userCode);
}
