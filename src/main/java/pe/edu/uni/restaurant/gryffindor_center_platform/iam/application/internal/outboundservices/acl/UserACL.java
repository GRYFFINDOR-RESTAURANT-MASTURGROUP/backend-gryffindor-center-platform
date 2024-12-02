package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl.UserContextFacade;

/**
 * Clase UserACL
 * Servicio para verificar restricciones de acceso relacionadas con usuarios.
 */
@Service
public class UserACL {
    private final UserContextFacade userContextFacade;

    public UserACL(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    /**
     * Verifica si el nombre de usuario es válido.
     * @param userName el nombre de usuario a validar.
     * @return true si el nombre de usuario es válido, false en caso contrario.
     */
    public boolean isValidUserName(String userName) {
        return userContextFacade.getUserNameFromUser(userName).isPresent();
    }
}
