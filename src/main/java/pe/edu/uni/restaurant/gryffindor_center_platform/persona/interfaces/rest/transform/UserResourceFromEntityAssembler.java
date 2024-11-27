package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(),
                entity.getUserCode(),
                entity.getNombreCompleto(),
                entity.getRole(),
                entity.getEmail());
    }
}
