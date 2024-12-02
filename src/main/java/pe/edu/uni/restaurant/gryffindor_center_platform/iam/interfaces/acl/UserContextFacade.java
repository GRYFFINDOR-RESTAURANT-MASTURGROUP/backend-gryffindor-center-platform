package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.domain.model.aggregates.User;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.Optional;

/**
 * UserContextFacade
 * <p>
 *     Esta clase actúa como una fachada para el contexto del usuario. Proporciona un punto de entrada simple
 *     para interactuar con el repositorio de usuarios y obtener información relacionada con los usuarios.
 * </p>
 */
@Service
public class UserContextFacade {
    private final UserRepository userRepository;

    /**
     * Constructor de la clase UserContextFacade.
     * @param userRepository El repositorio de usuarios.
     */
    public UserContextFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene el nombre de usuario a partir del nombre de usuario proporcionado.
     * @param userName El nombre de usuario del cual se desea obtener el nombre de usuario.
     * @return Un Optional con el nombre de usuario si se encuentra, o vacío si no se encuentra.
     */
    public Optional<String> getUserNameFromUser(String userName) {
        return userRepository.findByUserName(userName)
                .map(User::getUserName);
    }
}
