package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetReservationByUUIDQuery;
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
    public Optional<Reservation> handle(GetReservationByUUIDQuery query) {
        return this.reservationRepository.findByUserCode(query.userCode());
    }

    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.id());
    }

    @Override
    public List<Reservation> handle(GetAllReservationQuery query) {
        return this.reservationRepository.findAll();
    }

}
