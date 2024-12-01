package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllMesasQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetMesaByCantidadSillas;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetMesaByIdQuery;

import java.util.List;
import java.util.Optional;

public interface MesaQueryService {
    Optional<Mesa> handle(GetMesaByIdQuery query);
    List<Mesa> handle(GetAllMesasQuery query);
    Optional<Mesa> handle(GetMesaByCantidadSillas query);
}
