package es.cic.curso25.proy014.controller;

import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.model.Vehiculo;
import es.cic.curso25.proy014.service.PlazaService;
import es.cic.curso25.proy014.service.VehiculoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plazas")
public class PlazaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlazaController.class);

    @Autowired
    private PlazaService plazaService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public List<Plaza> getAllPlazas() {
        LOGGER.info("Obteniendo todas las plazas");
        return plazaService.getAllPlazas();
    }

    @GetMapping("/{id}")
    public Optional<Plaza> getPlaza(@PathVariable Long id) {
        LOGGER.info("Obteniendo plaza con id {}", id);

        Optional<Plaza> plaza = plazaService.getPlaza(id);
        return plaza;
    }

    @PostMapping
    public Plaza createPlaza(@RequestBody Plaza plaza) {

        LOGGER.info("Creando nueva plaza");

        if(plaza.getId() != null )
            throw new SecurityException("Para crear una plaza, el id debe ser nulo");
        
        if(!plaza.haySitio())
            throw new SecurityException("No hay sitio. Están todas las plazas ocupadas.");

        return plazaService.createPlaza(plaza);
    }

    @PutMapping("/{id}")
    public Plaza updatePlaza(@PathVariable Long numPlaza, @RequestBody Plaza plazaModificada) {

        if(plazaModificada.getId() == null || !plazaModificada.getId().equals(numPlaza)){
            throw new SecurityException("Para actualizar una plaza, el id debe coincidir con el del objeto enviado y no ser nulo");
              // Verificamos que el id no es nulo y que además coincide con el id del objeto enviado
        }
	else {
          LOGGER.info("Actualizando plaza con id {}", plazaModificada.getId());
		if(plazaService.getPlaza(numPlaza).isEmpty())
			throw new SecurityException("Plaza con id " + numPlaza + " no encontrada");
		// Miro que la plaza existe

		Plaza miPlaza = plazaService.getPlaza(numPlaza).get();
        // Ahora que se que existe, me la guardo
        LOGGER.info("Actualizando plaza con id {}", miPlaza.getId());

        if(plazaModificada.getSector() != null)
          miPlaza.setSector(plazaModificada.getSector());
        // En principio en la plaza solo se podría modificar el sector, pero compruebo que no sea nulo

        return plazaService.updatePlaza(plazaModificada);
    }
}


    @DeleteMapping("/{id}")
     public void deletePlaza(@PathVariable Long id) {

        LOGGER.info("Eliminando plaza con id {}", id);

        plazaService.deletePlaza(id);
    }

    @PostMapping("/{idPlaza}/agregarvehiculo")
    public Plaza agregarVehiculo(@PathVariable Long idPlaza, @RequestBody Vehiculo vehiculo) {
       if (vehiculo.getId() != null) 
            throw new SecurityException("No se ha encontrado el vehiculo con id " + vehiculo.getId() + ".");

        if(plazaService.getPlaza(idPlaza).isEmpty())
            throw new SecurityException("Error: Plaza con id " + idPlaza + " no encontrada");

        LOGGER.info("Agregando vehiculo a plaza con id {}", idPlaza);

        Plaza plaza = plazaService.getPlaza(idPlaza).get();

        plaza.agregarVehiculo(vehiculo);
        plazaService.updatePlaza(plaza);
        vehiculoService.createVehiculo(vehiculo);

        LOGGER.info("Vehiculo agregado a plaza con id {}", idPlaza);

        return plaza;
}

    @Transactional
    @PostMapping("/{idPlaza}/aparcarvehiculo")
    public void aparcaVehiculo(@PathVariable Long idPlaza, @RequestBody Vehiculo vehiculo) {
        
        if (vehiculo.getId() != null && vehiculoService.getVehiculo(vehiculo.getId()).isEmpty()) 
            throw new SecurityException("No se ha encontrado el vehiculo con id " + vehiculo.getId() + ".");

        if(plazaService.getPlaza(idPlaza).isEmpty())
            throw new SecurityException("Error: Plaza con id " + idPlaza + " no encontrada");

        LOGGER.info("Aparcando vehiculo en plaza con id {}", idPlaza);
        Plaza plaza = plazaService.getPlaza(idPlaza).get();

        vehiculo.setPlaza(plaza);
        vehiculo.setAparcado(true);
        
        
        plaza.getVehiculos().add(vehiculo);
        //plaza.estacionarVehiculo(vehiculo);

        plazaService.updatePlaza(plaza);
        vehiculoService.updateVehiculo(vehiculo);
       
}
}
