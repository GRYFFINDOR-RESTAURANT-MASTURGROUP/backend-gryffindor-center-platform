package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.outboundservices.acl.TestingUserACL;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllReservationQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetReservationByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.CreateReservationResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.ReservationResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.CreateReservationCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.ReservationResourceFromEntityAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.UpdateReservationCommandFromResourceAssembler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST controller that provides endpoints for managing reservations.
 */
@CrossOrigin(origins = "**", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservations", description = "Reservations Endpoints")
public class ReservationController {

    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;
    private final TestingUserACL testingUserACL;

    public ReservationController(
            ReservationCommandService reservationCommandService,
            ReservationQueryService reservationQueryService,
            TestingUserACL testingUserACL) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
        this.testingUserACL = testingUserACL;
    }

    /**
     * Endpoint to create a new reservation
     *
     * @param resource the resource containing reservation details
     * @return the created reservation
     */
    @PostMapping("/adding-reservations")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationResource resource) {

        UUID userCodeUser = resource.userCode();

        if (!testingUserACL.isValidUserCode(userCodeUser)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Invalid userCodeUser: TestingUser does not exist");
        }

        var createReservationCommand = CreateReservationCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var id = this.reservationCommandService.handle(createReservationCommand);

        if (id.equals(0L)) {
            return ResponseEntity.badRequest().body("Failed to create reservation.");
        }

        var getReservationByIdQuery = new GetReservationByIdQuery(id);
        var optionalReservation = this.reservationQueryService.handle(getReservationByIdQuery);

        if (optionalReservation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Reservation was created but could not be retrieved.");
        }

        var reservationResource = ReservationResourceFromEntityAssembler
                .toResourceFromEntity(optionalReservation.get());

        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all reservations
     *
     * @return a list of reservation resources
     */
    @GetMapping("/all-reservations")
    public ResponseEntity<List<ReservationResource>> getAllReservations() {
        var getAllReservationQuery = new GetAllReservationQuery();
        var reservations = this.reservationQueryService.handle(getAllReservationQuery);

        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var reservationResources = reservations.stream()
                .map(ReservationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservationResources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResource> updateReservation(@PathVariable Long id, @RequestBody ReservationResource resource) {
        var updateReservationCommand = UpdateReservationCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalReservation = this.reservationCommandService.handle(updateReservationCommand);

        if (optionalReservation.isEmpty())
            return ResponseEntity.badRequest().build();
        var reservationResource = ReservationResourceFromEntityAssembler.toResourceFromEntity(optionalReservation.get());
        return ResponseEntity.ok(reservationResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        var deleteReservationCommand = new DeleteReservationCommand(id);
        this.reservationCommandService.handle(deleteReservationCommand);
        return ResponseEntity.noContent().build();
    }


}

