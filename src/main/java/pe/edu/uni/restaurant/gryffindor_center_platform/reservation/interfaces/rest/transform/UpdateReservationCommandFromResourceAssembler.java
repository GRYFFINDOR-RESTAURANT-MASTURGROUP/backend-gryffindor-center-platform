package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.ReservationResource;

public class UpdateReservationCommandFromResourceAssembler {
  public static UpdateReservationCommand toCommandFromResource(Long id, ReservationResource resource) {
    return new UpdateReservationCommand(id, resource.reservedId(),
            resource.fechaReserva(),
            resource.horaReserva(),
            resource.customerQuantity(),
            resource.status(),
            resource.nombreCompletoUsuario(),
            resource.correoUsuario());
  }
}
