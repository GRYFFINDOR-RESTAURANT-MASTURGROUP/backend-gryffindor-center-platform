package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;

import java.sql.Time;
import java.util.Date;

public record CreateReservationCommand(
    Long reservedId,
    Date fechaReserva,
    Time horaReserva,
    Integer customerQuantity,
    Status status,
    String nombreCompletoUsuario,
    String correoUsuario
){
}
