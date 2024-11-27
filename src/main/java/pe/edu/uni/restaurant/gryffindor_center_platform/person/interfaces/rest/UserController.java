package pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.queries.GetUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.services.UserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.services.UserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.resources.CreateUserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.resources.UserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.interfaces.rest.transform.UserResourceFromEntityAssembler;

/**
 * REST controller that provides endpoints for managing inventory items.
 * @author Oscar Gabriel Aranda Vallejos
 */
@CrossOrigin(origins = "*", methods = { RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Endpoints")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    /**
     * Post Method
     * @author Oscar Gabriel Aranda Vallejos
     */
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource){
        var createUserCommand = CreateUserCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var id = this.userCommandService.handle(createUserCommand);

        if (id.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getUserByIdQuery = new GetUserByIdQuery(id);
        var optionalUser = this.userQueryService.handle(getUserByIdQuery);

        var reservationResource = UserResourceFromEntityAssembler.toResourceFromEntity(optionalUser.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.OK);
    }
}
