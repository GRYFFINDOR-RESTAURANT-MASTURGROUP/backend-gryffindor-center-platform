package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands;

public record CreateMesaCommand(Integer cantidadSillas,
                                boolean estado) {
}
