package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


/**
 * Entidad Mesa
 */
@Entity
@Table(name = "mesas")
@Getter
@Setter
@NoArgsConstructor
public class Mesa extends AuditableAbstractAggregateRoot<Mesa> {
    /**
     * Cantidad de sillas
     */
    @NotNull
    @Min(1)
    @Max(8)
    @Column(name = "cantidad_sillas")
    private Integer cantidadSillas;

    /**
     * Estado ( true: disponible; false: ocupada )
     */
    @NotNull
    @Column(name = "estado")
    private boolean estado;

    /**
     * Mesas asignadas para una determinada reservacion
     */
    @NotNull
    @Min(1)
    @Column(name = "reservacion_id")
    private Long reservacionId;

    /**
     * Constructor
     *
     */
    public Mesa(CreateMesaCommand command){
        this.cantidadSillas = command.cantidadSillas();
        this.estado = command.estado();
        this.reservacionId = command.reservacionId();
    }

    public void updateInformation(Integer cantidadSillas,
                                  boolean estado,
                                  Long reservacionId) {
        this.cantidadSillas = cantidadSillas;
        this.estado = estado;
        this.reservacionId = reservacionId;
    }
}
