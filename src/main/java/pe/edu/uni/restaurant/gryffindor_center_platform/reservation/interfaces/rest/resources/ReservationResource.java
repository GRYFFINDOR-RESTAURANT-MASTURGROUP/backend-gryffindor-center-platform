package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.UserCode;

import java.util.Date;
import java.util.UUID;

public record ReservationResource(
        Long id,
        Long reservedId,
        UserCode userCode,
        Date startDate,
        Date endDate,
        Integer customerQuantity,
        Status status
){
}
