package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.MesaResource;

public class UpdateMesaCommandFromResourceAssembler {
    public static UpdateMesaCommand toCommandFromResource(Long id, MesaResource resource) {
        return new UpdateMesaCommand(id, resource.cantidadSillas(),
                resource.estado());
    }
}
