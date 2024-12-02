package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Reservation;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.ReservationQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationQueryServiceImpl implements ReservationQueryService {
    private final ReservationRepository reservationRepository;

    public ReservationQueryServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Maneja la consulta de una reserva por su ID.
     * @param query la consulta que contiene el ID de la reserva.
     * @return la reserva correspondiente al ID proporcionado.
     */
    @Override
    public Optional<Reservation> handle(GetReservationByIdQuery query) {
        return this.reservationRepository.findById(query.id());
    }

    /**
     * Maneja la consulta para obtener todas las reservas.
     * @param query la consulta para obtener todas las reservas.
     * @return una lista con todas las reservas.
     */
    @Override
    public List<Reservation> handle(GetAllReservationQuery query) {
        return this.reservationRepository.findAll();
    }

    /**
     * Implementación de la consulta para obtener una reserva por el nombre completo del usuario.
     * @param query la consulta que contiene el nombre completo del usuario.
     * @return una reserva que corresponde al nombre completo del usuario.
     */
    @Override
    public Optional<Reservation> handle(GetReservationByNombreCompletoUsuarioQuery query) {
        return this.reservationRepository.findByNombreCompletoUsuario(query.nombreCompletoUsuario());
    }

    /**
     * Implementación de la consulta para obtener una reserva por el correo del usuario.
     * @param query la consulta que contiene el correo del usuario.
     * @return una reserva que corresponde al correo del usuario.
     */
    @Override
    public Optional<Reservation> handle(GetReservationByCorreoUsuarioQuery query) {
        return this.reservationRepository.findByCorreoUsuario(query.correoUsuario());
    }
}
