package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.entities.Role;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.queries.GetRoleByNameQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.services.RoleQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Clase RoleQueryServiceImpl
 * Esta clase se utiliza para manejar las consultas de roles
 */
@Service
public class RoleQueryServiceImpl implements RoleQueryService {
  private final RoleRepository roleRepository;

  /**
   * Constructor de RoleQueryServiceImpl
   * @param roleRepository el repositorio de roles
   */
  public RoleQueryServiceImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  /**
   * Maneja la consulta para obtener todos los roles
   * @param query la consulta para obtener todos los roles
   * @return List<Role> la lista de roles
   */
  @Override
  public List<Role> handle(GetAllRolesQuery query) {
    return roleRepository.findAll();
  }

  /**
   * Maneja la consulta para obtener un rol por nombre
   * @param query la consulta para obtener un rol por nombre
   * @return Optional<Role> el rol encontrado
   */
  @Override
  public Optional<Role> handle(GetRoleByNameQuery query) {
    return roleRepository.findByName(query.name());
  }
}
