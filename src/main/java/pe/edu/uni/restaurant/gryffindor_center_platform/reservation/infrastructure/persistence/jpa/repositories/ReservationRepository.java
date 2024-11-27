package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByCodigoUsuario(UUID codigoUsuario);
    
    Optional<Reservation> findByNombreCompletoUsuario(String nombreCompletoUsuario);

    Optional<Reservation> findByCorreoUsuario(String correoUsuario);
}
