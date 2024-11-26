package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Mesa, Long> {
    default List<Mesa> findByUserCodeUser(UUID userCodeUser) {
        return null;
    }

    Optional<Mesa> findByUserCode(UUID userCode);
    boolean existsByUserCode(UUID userCode);
}
