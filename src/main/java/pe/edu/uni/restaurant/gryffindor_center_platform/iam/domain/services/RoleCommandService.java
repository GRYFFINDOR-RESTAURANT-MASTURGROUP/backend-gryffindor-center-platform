package pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SeedRolesCommand;

/**
 * Interfaz RoleCommandService.
 * <p>
 *     Esta interfaz define el servicio encargado de manejar los comandos relacionados con los roles en el sistema.
 *     En este caso, se utiliza para manejar el comando de "SeedRolesCommand" que siembra los roles predeterminados en el sistema.
 * </p>
 */
public interface RoleCommandService {

  /**
   * Maneja el comando para sembrar los roles en el sistema.
   * @param command el comando SeedRolesCommand que contiene la información de los roles a sembrar.
   */
  void handle(SeedRolesCommand command);
}
