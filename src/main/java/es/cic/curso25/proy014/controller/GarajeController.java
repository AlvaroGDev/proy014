package es.cic.curso25.proy014.controller;

import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.service.GarajeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/garajes")
public class GarajeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GarajeController.class);

    @Autowired
    private GarajeService garajeService;

    @GetMapping
    public List<Garaje> getAllGarajes() {
        LOGGER.info("Obteniendo todos los garajes");
        return garajeService.getAllGarajes();
    }

    @GetMapping("/{id}")
    public Optional<Garaje> getGaraje(@PathVariable Long id) {
        LOGGER.info("Obteniendo garaje con id {}", id);

        Optional<Garaje> garaje = garajeService.getGaraje(id);
        return garaje;
    }

    @PostMapping
    public ResponseEntity<Garaje> createGaraje(@RequestBody Garaje garaje) {
        LOGGER.info("Creando nuevo garaje");
        Garaje creado = garajeService.saveGaraje(garaje);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Garaje> updateGaraje(@PathVariable Long id, @RequestBody Garaje garaje) {
        LOGGER.info("Actualizando garaje con id {}", id);
        garaje.setId(id);
        Garaje actualizado = garajeService.saveGaraje(garaje);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGaraje(@PathVariable Long id) {
        LOGGER.info("Eliminando garaje con id {}", id);
        garajeService.deleteGaraje(id);
        return ResponseEntity.noContent().build();
    }
}
