package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(String userName, /*String dni,*/ String password, List<String> roles) {
}

