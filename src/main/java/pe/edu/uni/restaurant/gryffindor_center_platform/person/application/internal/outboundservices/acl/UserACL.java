package pe.edu.uni.restaurant.gryffindor_center_platform.person.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.acl.UserContextFacade;

import java.util.UUID;

@Service
public class UserACL {
    private final UserContextFacade userContextFacade;

    public UserACL(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean isValidUserCode(UUID userCode) {
        return userContextFacade.getUserCodeUser(userCode).isPresent();
    }
}


