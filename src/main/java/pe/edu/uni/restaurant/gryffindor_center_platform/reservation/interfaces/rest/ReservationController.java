package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.acl.UserACL;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
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

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST que proporciona los puntos finales para gestionar las reservaciones.
 */
@CrossOrigin(origins = "**", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/reservaciones", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reservaciones", description = "Endpoint para el manejo de reservaciones")
public class ReservationController {

    private final ReservationCommandService reservationCommandService;
    private final ReservationQueryService reservationQueryService;
    private final UserACL userACL;

    public ReservationController(
            ReservationCommandService reservationCommandService,
            ReservationQueryService reservationQueryService,
            UserACL userACL) {
        this.reservationCommandService = reservationCommandService;
        this.reservationQueryService = reservationQueryService;
        this.userACL = userACL;
    }

    /**
     * Endpoint para crear una nueva reservación.
     *
     * @param resource el recurso que contiene los detalles de la reservación
     * @return la reservación creada
     */
    @PostMapping("/agregar-reservaciones")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservationResource resource) {

        String userNameFromUser = resource.nombreCompletoUsuario();

        if (!userACL.isValidUserName(userNameFromUser)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Nombre de usuario invalido: El usuario no se " +
                            "encuentra registrado");
        }

        // Validar el formato de horaReserva
        if (!isValidTime(resource.horaReserva())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Hora de Reserva invalida: coloque el formato adecuado " +
                            "HH:mm:ss");
        }

        var createReservationCommand = CreateReservationCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var id = this.reservationCommandService.handle(createReservationCommand);

        if (id.equals(0L)) {
            return ResponseEntity.badRequest().body("Error al crear la reservación.");
        }

        var getReservationByIdQuery = new GetReservationByIdQuery(id);
        var optionalReservation = this.reservationQueryService.handle(getReservationByIdQuery);

        if (optionalReservation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("La reservación fue creada pero no se pudo recuperar.");
        }

        var reservationResource = ReservationResourceFromEntityAssembler
                .toResourceFromEntity(optionalReservation.get());

        return new ResponseEntity<>(reservationResource, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todas las reservaciones.
     *
     * @return una lista de recursos de reservaciones
     */
    @GetMapping("/todas-las-reservaciones")
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

    /**
     * Valida si la hora de la reserva tiene el formato adecuado.
     * Además definimos el rango de horas permitidas para realizar una reservación.
     *
     * @param time el objeto Time a validar
     * @return verdadero si la hora es válida y está dentro del rango permitido, falso en caso contrario
     */
    private boolean isValidTime(Time time) {
        // Se podrá reservar en el rango de horas de 09:00:00 hasta 21:00:00
        LocalTime startTime = LocalTime.of(9, 0, 0);
        LocalTime endTime = LocalTime.of(21, 0, 0);

        LocalTime localTime = time.toLocalTime();

        // Verificamos si la hora de la reserva está en el rango permitido
        return !localTime.isBefore(startTime) && !localTime.isAfter(endTime);
    }
}
