package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.application.internal.outboundservices.acl.UserACL;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationCommandService;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class ReservationContextFacade {
    private final ReservationCommandService reservationCommandService;
    private final UserACL userACL;

    public ReservationContextFacade(ReservationCommandService reservationCommandService,
                                    UserACL userACL) {
        this.reservationCommandService = reservationCommandService;
        this.userACL = userACL;
    }

    public Long createReservation(Long reservedId,
                                      UUID userCode,
                                      Date startDate,
                                      Date endDate,
                                      Integer customerQuantity,
                                      Status status) {

        // Validar el `userNationalProviderIdentifier`
        if (!userACL.isValidUserCode(userCode)) {
            throw new IllegalArgumentException("Invalid userNationalProviderIdentifier: Mesa does not exist");
        }

        // Crear el comando
        var createReservationCommand = new CreateReservationCommand(reservedId,
                userCode, startDate, endDate, customerQuantity, status);

        // Manejar el comando
        var nationalProviderIdentifier = reservationCommandService.handle(createReservationCommand);

        return Objects.requireNonNullElse(nationalProviderIdentifier, 0L);
    }
}
