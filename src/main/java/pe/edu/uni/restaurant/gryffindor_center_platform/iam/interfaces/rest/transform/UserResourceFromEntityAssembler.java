package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {

  public static UserResource toResourceFromEntity(User user) {
    var roles = user.getRoles().stream()
        .map(Role::getStringName)
        .toList();
    return new UserResource(user.getId(), user.getUserName(), roles);
  }
}
