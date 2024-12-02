package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands;

/**
 * Comando SignInCommand.
 * Este comando se utiliza para realizar el inicio de sesión de un usuario.
 */
public record SignInCommand(String userName, String password) {
}
