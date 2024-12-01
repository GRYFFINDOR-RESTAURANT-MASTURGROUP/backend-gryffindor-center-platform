package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.commandservices;


import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.CreateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.UpdateMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.MesaCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.MesaRepository;

import java.util.Optional;

@Service
public class MesaCommandServiceImpl implements MesaCommandService {

    private final MesaRepository mesaRepository;

    public MesaCommandServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Long handle(CreateMesaCommand command) {
        var mesa = new Mesa(command);
        try {
            this.mesaRepository.save(mesa);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving mesa: " + e.getMessage());
        }
        return mesa.getId();
    }

    @Override
    public Optional<Mesa> handle(UpdateMesaCommand command) {
        var id = command.id();

        // If the mesa does not exist, throw an exception
        if (!this.mesaRepository.existsById(id)) {
            throw new IllegalArgumentException("Mesa with id " + id + " does not exist");
        }

        var mesaToUpdate = this.mesaRepository.findById(id).get();
        mesaToUpdate.updateInformation(command.cantidadSillas(),
                command.estado());

        try {
            var updatedMesa = this.mesaRepository.save(mesaToUpdate);
            return Optional.of(updatedMesa);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating mesa: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMesaCommand command) {
        // If the mesa does not exist, throw an exception
        if (!this.mesaRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Mesa with id " + command.id() + " does not exist");
        }

        // Try to delete the mesa, if an error occurs, throw an exception
        try {
            this.mesaRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting mesa: " + e.getMessage());
        }
    }
}
