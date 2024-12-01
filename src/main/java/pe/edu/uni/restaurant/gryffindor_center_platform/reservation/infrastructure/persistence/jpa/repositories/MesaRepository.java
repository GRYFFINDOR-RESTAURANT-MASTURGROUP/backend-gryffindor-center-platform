package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;

import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    Optional<Mesa> findByCantidadSillas(Integer cantidadSillas);
}
