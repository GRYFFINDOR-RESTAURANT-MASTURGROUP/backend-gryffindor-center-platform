package pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.commands.CreateUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource){
        return new CreateUserCommand(resource.userCode(),
                resource.firstName(),
                resource.lastName(),
                resource.role());
    }
}
