package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;

import java.util.Date;
import java.util.UUID;

public record CreateReservationCommand(
    Long reservedId,
    UUID userCode,
    Date startDate,
    Date endDate,
    Integer customerQuantity,
    Status status
){
}
