package pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.queries.GetTestingUserByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services.TestingUserCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.services.TestingUserQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.CreateTestingUserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.resources.TestingUserResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform.CreateTestingUserCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.interfaces.rest.transform.TestingUserResourceFromEntityAssembler;

/**
 * REST controller that provides endpoints for managing testing users.
 *
 */
@CrossOrigin(origins = "**", methods = { RequestMethod.POST})
@RestController
@RequestMapping(value = "/api/v1/testing-users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Testing Users", description = "TestingUser Endpoints")
public class TestingUserController {
    private final TestingUserCommandService testingUserCommandService;
    private final TestingUserQueryService testingUserQueryService;

    public TestingUserController(TestingUserCommandService testingUserCommandService, TestingUserQueryService testingUserQueryService) {
        this.testingUserCommandService = testingUserCommandService;
        this.testingUserQueryService = testingUserQueryService;
    }

    /**
     * Post Method
     *
     */
    @PostMapping("/adding-testing-users")
    public ResponseEntity<TestingUserResource> createTestingUser(@RequestBody CreateTestingUserResource resource){
        var createTestingUserCommand = CreateTestingUserCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var id = this.testingUserCommandService.handle(createTestingUserCommand);

        if (id.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getTestingUserByIdQuery = new GetTestingUserByIdQuery(id);
        var optionalTestingUser = this.testingUserQueryService.handle(getTestingUserByIdQuery);

        var reservationResource = TestingUserResourceFromEntityAssembler.toResourceFromEntity(optionalTestingUser.get());
        return new ResponseEntity<>(reservationResource, HttpStatus.OK);
    }
}
