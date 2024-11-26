package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateReservationCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.valueobjects.UserCode;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;
import java.util.UUID;

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
            @AttributeOverride(name = "UserCode", column = @Column(name = "user_code", nullable = false))
    })
    private UserCode userCode;

    /**
     *
     */
    @NotNull
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    /**
     *
     */
    @NotNull
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    /**
     *
     */
    @NotNull
    @Min(1)
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
     * Constructor
     *
     */
    public Reservation(CreateReservationCommand command){
        this.reservedId = command.reservedId();
        this.userCode = new UserCode(command.userCode());
        this.startDate = command.startDate();
        this.endDate = command.endDate();
        this.customerQuantity = command.customerQuantity();
        this.status = command.status();
    }

    public Reservation updateInformation(Long reservedId,
                                         UserCode userCode,
                                         Date startDate,
                                         Date endDate,
                                         int customerQuantity,
                                         Status status) {
        this.reservedId = reservedId;
        this.userCode = userCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerQuantity = customerQuantity;
        this.status = status;
        return this;
    }
}
