package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.CreateReservationResource;

public class CreateReservationCommandFromResourceAssembler {
    public static CreateReservationCommand toCommandFromResource(CreateReservationResource resource){
        return new CreateReservationCommand(resource.reservedId(),
                resource.userCode(),
                resource.startDate(),
                resource.endDate(),
                resource.customerQuantity(),
                resource.status());
    }
}
