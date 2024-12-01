package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.CreateMesaResource;

public class CreateMesaCommandFromResourceAssembler {
    public static CreateMesaCommand toCommandFromResource(CreateMesaResource resource){
        return new CreateMesaCommand(resource.cantidadSillas(),
                resource.estado());
    }
}
