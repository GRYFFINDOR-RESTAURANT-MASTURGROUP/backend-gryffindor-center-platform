package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.MesaResource;

public class MesaResourceFromEntityAssembler {
    public static MesaResource toResourceFromEntity(Mesa entity){
        return new MesaResource(entity.getCantidadSillas(), entity.isEstado());
    }
}
