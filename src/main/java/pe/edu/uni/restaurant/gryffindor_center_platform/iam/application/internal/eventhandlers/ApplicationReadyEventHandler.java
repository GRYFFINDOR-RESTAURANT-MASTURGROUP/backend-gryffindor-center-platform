package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.eventhandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.commands.SeedRolesCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.RoleCommandService;

import java.sql.Timestamp;

/**
 * Clase ApplicationReadyEventHandler
 * Esta clase se utiliza para manejar el evento ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
  private final RoleCommandService roleCommandService;
  private static final Logger LOGGER
      = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

  public ApplicationReadyEventHandler(RoleCommandService roleCommandService) {
    this.roleCommandService = roleCommandService;
  }

  /**
   * Maneja el evento ApplicationReadyEvent
   * Este método se utiliza para inicializar los roles si es necesario
   * @param event el ApplicationReadyEvent, el evento a manejar
   */
  @EventListener
  public void on(ApplicationReadyEvent event) {
    var applicationName = event.getApplicationContext().getId();
    LOGGER.info("Iniciando la verificación de inicialización de roles para {} en {}",
        applicationName, currentTimestamp());

    var seedRolesCommand = new SeedRolesCommand();
    roleCommandService.handle(seedRolesCommand);
    LOGGER.info("Verificación de inicialización de roles finalizada para {} en {}",
        applicationName, currentTimestamp());
  }

  private Timestamp currentTimestamp() {
    return new Timestamp(System.currentTimeMillis());
  }
}
