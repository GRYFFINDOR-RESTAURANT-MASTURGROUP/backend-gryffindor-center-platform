package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id, String userName, List<String> roles) {

}
