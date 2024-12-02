package pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.uni.restaurant.gryffindor_center_platform.iam.application.internal.outboundservices.acl.UserACL;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.commands.DeleteMesaCommand;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetAllMesasQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.model.queries.GetMesaByIdQuery;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.MesaCommandService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.domain.services.MesaQueryService;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.CreateMesaResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.resources.MesaResource;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.CreateMesaCommandFromResourceAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.MesaResourceFromEntityAssembler;
import pe.edu.uni.restaurant.gryffindor_center_platform.reservation.interfaces.rest.transform.UpdateMesaCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador API REST que permite el manejo de operaciones CRUD para el endpoint de mesas.
 */
@CrossOrigin(origins = "**", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping(value = "/api/v1/mesas", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Mesas", description = "Endpoints para el manejo de mesas")
public class MesaController {

    private final MesaCommandService mesaCommandService;
    private final MesaQueryService mesaQueryService;

    public MesaController(
            MesaCommandService mesaCommandService,
            MesaQueryService mesaQueryService) {
        this.mesaCommandService = mesaCommandService;
        this.mesaQueryService = mesaQueryService;
    }

    /**
     * Endpoint para agregar una mesa.
     *
     * @param resource el recurso que contiene los detalles de la mesa.
     * @return la mesa creada.
     */
    @PostMapping("/agregar-mesas")
    public ResponseEntity<?> createMesa(@RequestBody CreateMesaResource resource) {

        var createMesaCommand = CreateMesaCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var id = this.mesaCommandService.handle(createMesaCommand);

        if (id.equals(0L)) {
            return ResponseEntity.badRequest().body("No se pudo crear la mesa.");
        }

        var getMesaByIdQuery = new GetMesaByIdQuery(id);
        var optionalMesa = this.mesaQueryService.handle(getMesaByIdQuery);

        if (optionalMesa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("La mesa fue creada pero no se pudo recuperar.");
        }

        var mesaResource = MesaResourceFromEntityAssembler
                .toResourceFromEntity(optionalMesa.get());

        return new ResponseEntity<>(mesaResource, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todas las mesas.
     *
     * @return una lista de recursos de mesas.
     */
    @GetMapping("/todas-las-mesas")
    public ResponseEntity<List<MesaResource>> getAllMesas() {
        var getAllMesasQuery = new GetAllMesasQuery();
        var mesas = this.mesaQueryService.handle(getAllMesasQuery);

        if (mesas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var mesaResources = mesas.stream()
                .map(MesaResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(mesaResources);
    }

    /**
     * Endpoint para actualizar una mesa.
     *
     * @param id el ID de la mesa.
     * @param resource el recurso con los nuevos detalles de la mesa.
     * @return la mesa actualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MesaResource> updateMesa(@PathVariable Long id, @RequestBody MesaResource resource) {
        var updateMesaCommand = UpdateMesaCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalMesa = this.mesaCommandService.handle(updateMesaCommand);

        if (optionalMesa.isEmpty())
            return ResponseEntity.badRequest().build();
        var mesaResource = MesaResourceFromEntityAssembler.toResourceFromEntity(optionalMesa.get());
        return ResponseEntity.ok(mesaResource);
    }

    /**
     * Endpoint para eliminar una mesa.
     *
     * @param id el ID de la mesa.
     * @return una respuesta sin contenido indicando que la operación fue exitosa.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMesa(@PathVariable Long id) {
        var deleteMesaCommand = new DeleteMesaCommand(id);
        this.mesaCommandService.handle(deleteMesaCommand);
        return ResponseEntity.noContent().build();
    }
}
