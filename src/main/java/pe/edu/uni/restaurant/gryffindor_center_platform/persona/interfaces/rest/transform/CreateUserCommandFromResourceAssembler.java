package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource){
        return new CreateUserCommand(resource.userCode(),
                resource.nombreCompleto(),
                resource.role(),
                resource.email());
    }
}
