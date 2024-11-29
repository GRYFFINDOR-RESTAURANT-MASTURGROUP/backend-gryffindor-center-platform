package pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.acl.TestingUserContextFacade;

import java.util.UUID;

@Service
public class TestingUserACL {
    private final TestingUserContextFacade testingUserContextFacade;

    public TestingUserACL(TestingUserContextFacade testingUserContextFacade) {
        this.testingUserContextFacade = testingUserContextFacade;
    }

    public boolean isValidUserCode(UUID userCode) {
        return testingUserContextFacade.getUserCodeUser(userCode).isPresent();
    }
}


