package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateReservationCommand;

import java.util.Optional;

public interface ReservationCommandService {
    Long handle(CreateReservationCommand command);

    Optional<Reservation> handle(UpdateReservationCommand command);

    void handle(DeleteReservationCommand command);
}
