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
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CodigoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.CorreoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.NombreCompletoUsuario;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.sql.Time;
import java.util.Date;

/**
 * Reservation Aggregate Root
 */
@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends AuditableAbstractAggregateRoot<Reservation> {

    /**
     * Reserved Id that must be greater than zero
     */
    @NotNull
    @Min(1)
    @Column(name = "reserved_id")
    private Long reservedId;

    /**
     * User code
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "CodigoUsuario", column = @Column(name = "user_code", nullable = false))
    })
    private CodigoUsuario codigoUsuario;

    /**
     *
     */
    @NotNull
    @Column(name = "fecha_reserva", nullable = false)
    private Date fechaReserva;

    /**
     *
     */
    @NotNull
    @Column(name = "end_date", nullable = false)
    private Time horaReserva;

    /**
     *
     */
    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "customer_quantity")
    private Integer customerQuantity;

    /**
     *
     */
    @NotNull
    @Column(name = "status")
    private Status status;

    /**
     * Person Type
     *//*
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "PersonType", column = @Column(name = "person_type", nullable = false))
    })
    private PersonType personType;*/

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
        this.reservedId = command.reservedId();
        this.codigoUsuario = new CodigoUsuario(command.userCode());
        this.fechaReserva = command.fechaReserva();
        this.horaReserva = command.horaReserva();
        this.customerQuantity = command.customerQuantity();
        this.status = command.status();
        this.nombreCompletoUsuario = new NombreCompletoUsuario(command.nombreCompletoUsuario());
        this.correoUsuario = new CorreoUsuario(command.correoUsuario());
    }

    public Reservation updateInformation(Long reservedId,
                                         CodigoUsuario codigoUsuario,
                                         Date fechaReserva,
                                         Time horaReserva,
                                         int customerQuantity,
                                         Status status,
                                         NombreCompletoUsuario nombreCompletoUsuario,
                                         CorreoUsuario correoUsuario) {
        this.reservedId = reservedId;
        this.codigoUsuario = codigoUsuario;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.customerQuantity = customerQuantity;
        this.status = status;
        this.nombreCompletoUsuario = nombreCompletoUsuario;
        this.correoUsuario = correoUsuario;
        return this;
    }
}
