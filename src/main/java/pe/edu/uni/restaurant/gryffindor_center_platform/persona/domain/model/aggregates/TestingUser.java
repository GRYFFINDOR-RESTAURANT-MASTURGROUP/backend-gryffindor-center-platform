package pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.commands.CreateTestingUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.uni.restaurant.gryffindor_center_platform.persona.domain.model.valueobjects.Role;

import java.util.UUID;

@Entity
@Table(name = "testing-users")
@Getter
@Setter
@NoArgsConstructor
public class TestingUser extends AuditableAbstractAggregateRoot<TestingUser> {

    /**
     * TestingUser Code
     */
    @NotNull
    @Column(name = "user_code", unique = true, nullable = false)
    private UUID userCode;

    /**
     * Nombre completo del usuario
     */
    @NotNull
    @Size(min = 8, message = "El nombre completo debe tener al menos 8 caracteres")
    @Column(name = "nombre_completo", unique = true, nullable = false)
    private String nombreCompleto;

    /**
     * TestingUser Status
     */
    @NotNull
    @Column(name = "role", nullable = false)
    private Role role;

    @NotNull
    @Email
    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String email;

    public TestingUser(CreateTestingUserCommand command){
        this.userCode = command.userCode();
        this.nombreCompleto = command.nombreCompleto();
        this.role = command.role();
        this.email = command.email();
    }
}
