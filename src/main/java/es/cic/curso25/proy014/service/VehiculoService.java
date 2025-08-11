package es.cic.curso25.proy014.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import es.cic.curso25.proy014.model.Vehiculo;
import es.cic.curso25.proy014.repository.VehiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoService.class);

	public List<Vehiculo> getAllVehiculos() {
		LOGGER.info("Obteniendo todos los vehiculos desde la base de datos");
	return vehiculoRepository.findAll();
	}

	public Optional<Vehiculo> getVehiculo(Long id) {
		LOGGER.info(String.format("Leído el vehiculo con id %s", id));
	return vehiculoRepository.findById(id);
	}

	 public Vehiculo createVehiculo(Vehiculo vehiculo) {
        LOGGER.info("Creación/actualización de plaza");
        return vehiculoRepository.save(vehiculo);
    }

	public Vehiculo updateVehiculo(Vehiculo vehiculo) {
		LOGGER.info("Creación/actualización de vehiculo");
	return vehiculoRepository.save(vehiculo);
	}

	public void deleteVehiculo(Long id) {
		LOGGER.info(String.format("Eliminando vehiculo con id %s", id));
	vehiculoRepository.deleteById(id);
	}

}
