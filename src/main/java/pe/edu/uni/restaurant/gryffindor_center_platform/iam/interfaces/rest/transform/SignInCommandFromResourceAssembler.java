package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SignInCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {

  public static SignInCommand toCommandFromResource(SignInResource signInResource) {
    return new SignInCommand(signInResource.userName(), /*signInResource.dni(),*/ signInResource.password());
  }
}
