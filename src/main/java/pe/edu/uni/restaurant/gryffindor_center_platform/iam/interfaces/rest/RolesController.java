package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.RoleQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources.RoleResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.transform.RoleResourceFromEntityAssembler;

import java.util.List;

/**
 *  Controlador de Roles
 *  Este controlador es responsable de manejar todas las solicitudes relacionadas con los roles.
 */
@RestController
@RequestMapping(value = "/ap/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Endpoint de los tipos de rol existentes")
public class RolesController {

  private final RoleQueryService roleQueryService;

  public RolesController(RoleQueryService roleQueryService) {
    this.roleQueryService = roleQueryService;
  }

  /**
   * Obtener todos los roles
   * @return Lista de recursos de roles
   * @see RoleResource
   */
  @GetMapping
  public ResponseEntity<List<RoleResource>> getAllRoles() {
    var getAllRolesQuery = new GetAllRolesQuery();
    var roles = roleQueryService.handle(getAllRolesQuery);
    var roleResources = roles.stream()
        .map(RoleResourceFromEntityAssembler::toResourceFromEntity)
        .toList();
    return ResponseEntity.ok(roleResources);
  }
}
