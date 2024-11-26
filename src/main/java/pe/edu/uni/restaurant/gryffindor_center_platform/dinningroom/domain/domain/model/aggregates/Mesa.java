package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.valueobjects.Status;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.UUID;

@Entity
@Table(name = "mesas")
@Getter
@Setter
@NoArgsConstructor
public class Mesa extends AuditableAbstractAggregateRoot<Mesa> {

    /**
     * Mesa Code
     */
    @NotNull
    @Column(name = "mesa_code", unique = true, nullable = false)
    private UUID mesaCode;

    /**
     * Mesa Status
     */
    @NotNull
    @Column(name = "role", unique = true, nullable = false)
    private Status role;


    public Mesa(CreateMesaCommand command){
        this.mesaCode = command.mesaCode();
        this.role = command.role();
    }
}
