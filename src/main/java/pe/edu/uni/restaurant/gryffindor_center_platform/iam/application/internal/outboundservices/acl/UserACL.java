package pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.acl.UserContextFacade;

@Service
public class UserACL {
    private final UserContextFacade userContextFacade;

    public UserACL(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean isValidUserName(String userName) {
        return userContextFacade.getUserNameFromUser(userName).isPresent();
    }
}
