package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetReservationByUUIDQuery;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByUUIDQuery query);
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationQuery query);

}
