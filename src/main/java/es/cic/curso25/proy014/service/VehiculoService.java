package es.cic.curso25.proy014.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso25.proy014.model.Vehiculo;
import es.cic.curso25.proy014.repository.CocheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class VehiculoService {

	@Autowired
	private CocheRepository cocheRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);

	public List<Vehiculo> getAllVehiculos() {
		LOGGER.info("Obteniendo todos los vehiculos desde la base de datos");
		return cocheRepository.findAll();
	}

	public Optional<Vehiculo> getVehiculo(Long id) {
		LOGGER.info(String.format("Leído el vehiculo con id %s", id));
		return cocheRepository.findById(id);
	}

	public Vehiculo saveCehiculo(Vehiculo coche) {
		LOGGER.info("Creación/actualización de vehiculo");
		return cocheRepository.save(coche);
	}

	public void deleteVehiculo(Long id) {
		LOGGER.info(String.format("Eliminando vehiculo con id %s", id));
		cocheRepository.deleteById(id);
	}

}
