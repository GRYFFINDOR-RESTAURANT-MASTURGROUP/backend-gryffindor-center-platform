package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.application.internal.outboundservices.acl.TestingUserACL;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationCommandService;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class ReservationContextFacade {
    private final ReservationCommandService reservationCommandService;
    private final TestingUserACL testingUserACL;

    public ReservationContextFacade(ReservationCommandService reservationCommandService,
                                    TestingUserACL userACL) {
        this.reservationCommandService = reservationCommandService;
        this.testingUserACL = userACL;
    }

    public Long createReservation(Long reservedId,
                                  UUID CodigoUsuario,
                                  Date fechaReserva,
                                  Time horaReserva,
                                  Integer customerQuantity,
                                  Status status,
                                  String nombreCompletoUsuario,
                                  String correoUsuario) {

        // Validar el `userNationalProviderIdentifier`
        if (!testingUserACL.isValidUserCode(CodigoUsuario)) {
            throw new IllegalArgumentException("Invalid userNationalProviderIdentifier: TestingUser does not exist");
        }

        // Crear el comando
        var createReservationCommand = new CreateReservationCommand(reservedId,
                CodigoUsuario, fechaReserva, horaReserva, customerQuantity, status,
                nombreCompletoUsuario, correoUsuario);

        // Manejar el comando
        var nationalProviderIdentifier = reservationCommandService.handle(createReservationCommand);

        return Objects.requireNonNullElse(nationalProviderIdentifier, 0L);
    }
}
