package pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.services;

import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.queries.GetMesaByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.dinningroom.domain.domain.model.queries.GetMesaByUUIDQuery;

import java.util.List;
import java.util.Optional;

public interface MesaQueryService {
    List<Mesa> handle(GetMesaByUUIDQuery query);
    Optional<Mesa> handle(GetMesaByIdQuery query);
}
