package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands;

public record UpdateMesaCommand(Long id,
                                Integer cantidadSillas,
                                boolean estado) {
}
