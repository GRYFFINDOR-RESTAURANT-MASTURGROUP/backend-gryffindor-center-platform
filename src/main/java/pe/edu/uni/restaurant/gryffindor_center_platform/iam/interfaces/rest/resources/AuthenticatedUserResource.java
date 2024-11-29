package pe.edu.uni.restaurant.gryffindor_center_platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
