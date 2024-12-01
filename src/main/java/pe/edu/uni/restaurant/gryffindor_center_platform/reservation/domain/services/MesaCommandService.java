package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateMesaCommand;

import java.util.Optional;

public interface MesaCommandService {
    Long handle(CreateMesaCommand command);

    Optional<Mesa> handle(UpdateMesaCommand command);

    void handle(DeleteMesaCommand command);
}
