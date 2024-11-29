package pe.edu.uni.restaurant.gryffindor_center_platform.persona.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestingUserRepository extends JpaRepository<TestingUser, Long> {
    default List<TestingUser> findByUserCodeUser(UUID userCodeUser) {
        return null;
    }

    Optional<TestingUser> findByUserCode(UUID userCode);
    boolean existsByUserCode(UUID userCode);
}
