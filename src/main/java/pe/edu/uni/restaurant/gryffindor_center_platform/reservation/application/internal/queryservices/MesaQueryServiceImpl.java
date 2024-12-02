package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.aggregates.Mesa;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllMesasQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetMesaByCantidadSillas;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetMesaByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.MesaQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.infrastructure.persistence.jpa.repositories.MesaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MesaQueryServiceImpl implements MesaQueryService {
    private final MesaRepository mesaRepository;

    public MesaQueryServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    /**
     * Maneja la consulta de una mesa por su ID.
     * @param query la consulta que contiene el ID de la mesa.
     * @return la mesa correspondiente al ID proporcionado.
     */
    @Override
    public Optional<Mesa> handle(GetMesaByIdQuery query) {
        return this.mesaRepository.findById(query.id());
    }

    /**
     * Maneja la consulta para obtener todas las mesas.
     * @param query la consulta para obtener todas las mesas.
     * @return una lista con todas las mesas.
     */
    @Override
    public List<Mesa> handle(GetAllMesasQuery query) {
        return this.mesaRepository.findAll();
    }

    /**
     * Implementación de la consulta para obtener una mesa por la cantidad de sillas.
     * @param query la consulta que contiene la cantidad de sillas.
     * @return una mesa que tiene la cantidad de sillas especificada.
     */
    @Override
    public Optional<Mesa> handle(GetMesaByCantidadSillas query) {
        return this.mesaRepository.findByCantidadSillas(query.cantidadSillas());
    }
}
