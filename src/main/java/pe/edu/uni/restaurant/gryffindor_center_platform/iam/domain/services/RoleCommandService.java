package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
