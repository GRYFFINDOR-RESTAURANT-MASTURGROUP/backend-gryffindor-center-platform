package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform;


import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates.TestingUser;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.TestingUserResource;

public class TestingUserResourceFromEntityAssembler {
    public static TestingUserResource toResourceFromEntity(TestingUser entity){
        return new TestingUserResource(entity.getId(),
                entity.getUserCode(),
                entity.getNombreCompleto(),
                entity.getRole(),
                entity.getEmail());
    }
}
