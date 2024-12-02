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
            throw new IllegalArgumentException("Error al guardar mesas: " + e.getMessage());
        }
        return mesa.getId();
    }

    @Override
    public Optional<Mesa> handle(UpdateMesaCommand command) {
        var id = command.id();

        // Si la mesa no existe al querer actualizar, se lanza un mensaje de error
        if (!this.mesaRepository.existsById(id)) {
            throw new IllegalArgumentException("Mesa con id " + id + " no existe");
        }

        var mesaToUpdate = this.mesaRepository.findById(id).get();
        mesaToUpdate.updateInformation(command.cantidadSillas(),
                command.estado(), command.reservacionId());

        try {
            var updatedMesa = this.mesaRepository.save(mesaToUpdate);
            return Optional.of(updatedMesa);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar la información de la mesa: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteMesaCommand command) {
        // Si la mesa no existe al querer eliminar, se lanza un mensaje de error
        if (!this.mesaRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Mesa con id " + command.id() + " no existe");
        }

        // Se intenta eliminar la mesa y si ocurre un error en el proceso, se lanza un mensaje de error
        try {
            this.mesaRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar la mesa: " + e.getMessage());
        }
    }
}
