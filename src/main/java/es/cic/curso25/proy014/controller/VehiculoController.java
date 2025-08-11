package es.cic.curso25.proy014.controller;

import es.cic.curso25.proy014.model.Vehiculo;
import es.cic.curso25.proy014.service.PlazaService;
import es.cic.curso25.proy014.service.VehiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoController.class);

	@Autowired
	private VehiculoService vehiculoService;

	@Autowired
	private PlazaService plazaService;

	@GetMapping
	public List<Vehiculo> getAllVehiculos() {
		LOGGER.info("Obteniendo todos los vehiculos");
		return vehiculoService.getAllVehiculos();
	}

	@GetMapping("/{id}")
	public Optional<Vehiculo> getVehiculo(@PathVariable Long id) {
		LOGGER.info("Obteniendo vehiculo con id {}", id);
		return vehiculoService.getVehiculo(id);
	}

	@PostMapping
	public Vehiculo createVehiculo(@PathVariable Long idPlaza, @RequestBody Vehiculo vehiculo) {
		if(vehiculo.getId() != null)
			throw new SecurityException("Para crear un vehiculo, el id debe ser nulo");

		LOGGER.info("Creando nuevo vehiculo");
		plazaService.getPlaza(idPlaza).get().getVehiculos().add(vehiculo);
		
		return vehiculoService.createVehiculo(vehiculo);
	}

	@PutMapping("/{id}")
	public Vehiculo updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculoModificado) {
	 if(vehiculoModificado.getId() == null || vehiculoModificado.getId() != id)
            throw new SecurityException("Para actualizar un vehiculo, el id debe coincidir con el del objeto enviado y no ser nulo");
        	// Verificamos que el id no es nulo y que además coincide con el id del objeto enviado
	
	else {
	
		if(vehiculoService.getVehiculo(id).isEmpty())
			throw new SecurityException("Vehiculo con id " + id + " no encontrado");
		
		Vehiculo mVehiculo = vehiculoService.getVehiculo(id).get();

        LOGGER.info("Actualizando vehiculo con id {}", mVehiculo.getId());

		if(vehiculoModificado.getColor() != null)
		mVehiculo.setColor(vehiculoModificado.getColor());

		if(vehiculoModificado.getModelo() != null)
		mVehiculo.setModelo(vehiculoModificado.getModelo());

		if(vehiculoModificado.getMatricula() != null)
		mVehiculo.setMatricula(vehiculoModificado.getMatricula());

		/*
		 * Como al actualizar un vehiculo mandamos el objeto entero, es posible que mandemos solo lo que queramos cambiar
		 * Por eso comprobamos si los campos son nulos antes de asignarlos
		 */
	
        return vehiculoService.updateVehiculo(mVehiculo);
	}

	

	}

	@DeleteMapping("/{id}")
	public void deleteVehiculo(@PathVariable Long id) {
		LOGGER.info("Eliminando vehiculo con id {}", id);
		vehiculoService.deleteVehiculo(id);
	}

}
