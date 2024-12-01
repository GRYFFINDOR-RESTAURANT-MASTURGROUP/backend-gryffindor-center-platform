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
     * Estado ( true: disponible; false: no disponible )
     */
    @NotNull
    @Column(name = "estado")
    private boolean estado;

    /**
     * Constructor
     *
     */
    public Mesa(CreateMesaCommand command){
        this.cantidadSillas = command.cantidadSillas();
        this.estado = command.estado();
    }

    public void updateInformation(Integer cantidadSillas,
                                  boolean estado) {
        this.cantidadSillas = cantidadSillas;
        this.estado = estado;
    }
}
