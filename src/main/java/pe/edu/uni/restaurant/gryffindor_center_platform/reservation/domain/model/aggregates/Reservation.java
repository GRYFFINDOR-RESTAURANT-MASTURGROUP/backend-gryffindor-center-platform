package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CorreoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.NombreCompletoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.sql.Time;
import java.util.Date;

/**
 * Entidad Reservación
 */
@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {
    /**
     * Fecha para la reservación
     */
    @NotNull
    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;

    /**
     * Hora exacta para la reservación
     */
    @NotNull
    @Column(name = "hora_reserva", nullable = false)
    private Time horaReserva;

    /**
     * Cantidad de personas
     */
    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "customer_quantity")
    private Integer customerQuantity;

    /**
     * Estado de la reserva ( 0: Pendiente; 1: Activa; 2: Cancelada )
     */
    @NotNull
    @Column(name = "status")
    private Status status;

    /**
     * Nombre Completo del usuario
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "NombreCompletoUsuario", column = @Column(name = "nombre_completo_usuario", nullable = false))
    })
    private NombreCompletoUsuario nombreCompletoUsuario;

    /**
     * Correo del usuario
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "CorreoUsuario", column = @Column(name = "correo_usuario", nullable = false))
    })
    private CorreoUsuario correoUsuario;


    /**
     * Constructor
     *
     */
    public Reservation(CreateReservationCommand command){
        this.fechaReserva = command.fechaReserva();
        this.horaReserva = command.horaReserva();
        this.customerQuantity = command.customerQuantity();
        this.status = command.status();
        this.nombreCompletoUsuario = new NombreCompletoUsuario(command.nombreCompletoUsuario());
        this.correoUsuario = new CorreoUsuario(command.correoUsuario());
    }

    public void updateInformation(Date fechaReserva,
                                  Time horaReserva,
                                  int customerQuantity,
                                  Status status,
                                  NombreCompletoUsuario nombreCompletoUsuario,
                                  CorreoUsuario correoUsuario) {
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.customerQuantity = customerQuantity;
        this.status = status;
        this.nombreCompletoUsuario = nombreCompletoUsuario;
        this.correoUsuario = correoUsuario;
    }
}
