package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(Mesa entity){
        return new UserResource(entity.getId(),
                entity.getUserCode(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getRole());
    }
}
