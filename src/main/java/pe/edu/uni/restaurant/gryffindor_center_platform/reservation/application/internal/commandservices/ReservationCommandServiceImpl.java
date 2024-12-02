package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;


import java.util.Optional;

@Service
public class ReservationCommandServiceImpl implements ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Long handle(CreateReservationCommand command) {
        var reservation = new Reservation(command);
        try {
            this.reservationRepository.save(reservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar la reservación: " + e.getMessage());
        }
        return reservation.getId();
    }

    @Override
    public Optional<Reservation> handle(UpdateReservationCommand command) {
        var id = command.id();

        // Si la reservacion no existe al querer actualizar, se lanza un error
        if (!this.reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Reservación con id " + id + " no existe");
        }

        var reservationToUpdate = this.reservationRepository.findById(id).get();
        reservationToUpdate.updateInformation(command.fechaReserva(),
                command.horaReserva(), command.customerQuantity(), command.status(),
                command.nombreCompletoUsuario(),command.correoUsuario());

        try {
            var updatedReservation = this.reservationRepository.save(reservationToUpdate);
            return Optional.of(updatedReservation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar la reservación: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteReservationCommand command) {
        // Si la reservacion no existe al querer eliminar, se lanza un error
        if (!this.reservationRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Reservación con id " + command.id() + " no existe");
        }

        // Intenta eliminar la reservación, si se produce una excepción, se lanza mensaje de error
        try {
            this.reservationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar la reservación: " + e.getMessage());
        }
    }
}
