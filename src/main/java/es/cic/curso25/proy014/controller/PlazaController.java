package es.cic.curso25.proy014.controller;

import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.service.PlazaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plazas")
public class PlazaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlazaController.class);

    @Autowired
    private PlazaService plazaService;

    @GetMapping
    public List<Plaza> getAllGarajes() {
        LOGGER.info("Obteniendo todos los garajes");
        return plazaService.getAllPlazas();
    }

    @GetMapping("/{id}")
    public Optional<Plaza> getGaraje(@PathVariable Long id) {
        LOGGER.info("Obteniendo garaje con id {}", id);

        Optional<Plaza> plaza = plazaService.getPlaza(id);
        return plaza;
    }

    @PostMapping
    public Plaza createGaraje(@RequestBody Plaza plaza) {

        LOGGER.info("Creando nuevo garaje");

        Plaza plazaCreada = plazaService.createPlaza(plaza);

        return plazaCreada;
    }

    @PutMapping("/{id}")

    public Plaza updateGaraje(@RequestBody Plaza plaza, Plaza plazaModificada) {

        LOGGER.info("Actualizando plaza con id {}", plaza.getNumPlaza());

        Plaza plazaActualizada = plazaService.createPlaza(plaza);
        return plazaActualizada;
    }

    @DeleteMapping("/{id}")
     public void deleteGaraje(@PathVariable Long id) {

        LOGGER.info("Eliminando garaje con id {}", id);

        plazaService.deletePlaza(id);
        

    }
}
