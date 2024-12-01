package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.id());
    }

    @Override
    public List<Reservation> handle(GetAllReservationQuery query) {
        return this.reservationRepository.findAll();
    }

    /**
     * Get Reservation By NombreCompletoUsuario Query Implementation
     */
    @Override
    public Optional<Reservation> handle(GetReservationByNombreCompletoUsuarioQuery query) {
        return this.reservationRepository.findByNombreCompletoUsuario(query.nombreCompletoUsuario());
    }

    /**
     * Get Reservation By CorreoUsuario Query Implementation
     */
    @Override
    public Optional<Reservation> handle(GetReservationByCorreoUsuarioQuery query) {
        return this.reservationRepository.findByCorreoUsuario(query.correoUsuario());
    }
}
