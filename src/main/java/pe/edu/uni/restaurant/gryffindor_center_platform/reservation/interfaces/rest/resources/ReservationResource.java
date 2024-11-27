package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CodigoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CorreoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.NombreCompletoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;

import java.sql.Time;
import java.util.Date;

public record ReservationResource(
        Long id,
        Long reservedId,
        CodigoUsuario codigoUsuario,
        Date fechaReserva,
        Time horaReserva,
        Integer customerQuantity,
        Status status,
        NombreCompletoUsuario nombreCompletoUsuario,
        CorreoUsuario correoUsuario
){
}
