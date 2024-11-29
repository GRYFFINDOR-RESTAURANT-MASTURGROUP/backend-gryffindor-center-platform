package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateTestingUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.CreateTestingUserResource;

public class CreateTestingUserCommandFromResourceAssembler {
    public static CreateTestingUserCommand toCommandFromResource(CreateTestingUserResource resource){
        return new CreateTestingUserCommand(resource.userCode(),
                resource.nombreCompleto(),
                resource.role(),
                resource.email());
    }
}
