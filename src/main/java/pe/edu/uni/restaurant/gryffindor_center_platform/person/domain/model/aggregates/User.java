package pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.commands.CreateUserCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.uni.restaurant.gryffindor_center_platform.person.domain.model.valueobjects.Role;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends AuditableAbstractAggregateRoot<User> {

    /**
     * User Code
     */
    @NotNull
    @Column(name = "user_code", unique = true, nullable = false)
    private UUID userCode;

    /**
     * User First Name
     */
    @NotNull
    @Column(name = "first_name", unique = true, nullable = false)
    private String firstName;

    /**
     * User Last Name
     */
    @NotNull
    @Column(name = "last_name", unique = true, nullable = false)
    private String lastName;

    /**
     * User Status
     */
    @NotNull
    @Column(name = "role", unique = true, nullable = false)
    private Role role;


    public User(CreateUserCommand command){
        this.userCode = command.userCode();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.role = command.role();
    }
}
