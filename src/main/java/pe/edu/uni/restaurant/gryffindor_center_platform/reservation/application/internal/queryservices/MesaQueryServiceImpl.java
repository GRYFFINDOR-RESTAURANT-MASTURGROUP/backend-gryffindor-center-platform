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

    @Override
    public Optional<Mesa> handle(GetMesaByIdQuery query) {
        return this.mesaRepository.findById(query.id());
    }

    @Override
    public List<Mesa> handle(GetAllMesasQuery query) {
        return this.mesaRepository.findAll();
    }

    /**
     * Get Mesa By NombreCompletoUsuario Query Implementation
     */
    @Override
    public Optional<Mesa> handle(GetMesaByCantidadSillas query) {
        return this.mesaRepository.findByCantidadSillas(query.cantidadSillas());
    }
}
