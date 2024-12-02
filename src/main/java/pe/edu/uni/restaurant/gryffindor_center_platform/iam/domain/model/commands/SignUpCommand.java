package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;

import java.util.List;

/**
 * Comando SignUpCommand.
 * Este comando se utiliza para registrar un nuevo usuario en el sistema.
 */
public record SignUpCommand(String userName, String password, List<Role> roles) {
}
