package pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(),
                entity.getUserCode(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getRole());
    }
}
