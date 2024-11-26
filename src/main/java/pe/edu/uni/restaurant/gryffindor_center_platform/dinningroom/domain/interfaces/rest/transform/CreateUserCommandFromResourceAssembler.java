package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateMesaCommand toCommandFromResource(CreateUserResource resource){
        return new CreateMesaCommand(resource.userCode(),
                resource.firstName(),
                resource.lastName(),
                resource.role());
    }
}
