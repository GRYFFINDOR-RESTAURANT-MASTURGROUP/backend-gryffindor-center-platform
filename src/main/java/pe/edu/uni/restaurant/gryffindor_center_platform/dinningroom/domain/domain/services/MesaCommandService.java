package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.services;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.commands.CreateMesaCommand;

@Service
public interface MesaCommandService {
    Long handle(CreateMesaCommand command);
}
