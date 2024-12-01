package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ReservationQueryService {
    Optional<Reservation> handle(GetReservationByIdQuery query);
    List<Reservation> handle(GetAllReservationQuery query);
    Optional<Reservation> handle(GetReservationByNombreCompletoUsuarioQuery query);
    Optional<Reservation> handle(GetReservationByCorreoUsuarioQuery query);
}
