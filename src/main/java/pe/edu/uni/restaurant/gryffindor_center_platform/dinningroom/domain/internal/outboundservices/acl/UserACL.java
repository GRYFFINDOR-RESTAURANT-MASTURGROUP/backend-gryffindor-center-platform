package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.interfaces.acl.MesaContextFacade;

import java.util.UUID;

@Service
public class UserACL {
    private final MesaContextFacade userContextFacade;

    public UserACL(MesaContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean isValidUserCode(UUID userCode) {
        return userContextFacade.getUserCodeUser(userCode).isPresent();
    }
}


