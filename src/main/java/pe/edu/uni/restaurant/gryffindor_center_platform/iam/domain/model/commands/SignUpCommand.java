package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String userName, /*String dni,*/ String password, List<Role> roles) {
}
