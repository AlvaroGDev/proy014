package es.cic.curso25.proy014.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.repository.CocheRepository;
import es.cic.curso25.proy014.repository.GarajeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class GarajeService {

    @Autowired
    private GarajeRepository garajeRepository;
    @Autowired
    private CocheRepository cocheRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(GarajeService.class); // Por si se usa en el futuro

    public List<Garaje> getAllGarajes() {
        LOGGER.info("Obteniendo todos los garajes desde la base de datos");
        return garajeRepository.findAll();
    }

    public Optional<Garaje> getGaraje(Long id) {
        LOGGER.info(String.format("Leído el garaje con id %s", id));
        return garajeRepository.findById(id);
    }

      public List<Coche> getAllCoches() {
        LOGGER.info("Obteniendo todos los coches desde la base de datos");
        return cocheRepository.findAll();
    }

    public Optional<Coche> getCoche(Long id) {
        LOGGER.info(String.format("Leído el coche con id %s", id));
        return cocheRepository.findById(id);
    }

    public Garaje saveGaraje(Garaje garaje) {
        LOGGER.info("Creación de garaje");
        return garajeRepository.save(garaje);
    }

    public Coche saveCoche(Coche coche) {
        LOGGER.info("Creación de coche");
        return cocheRepository.save(coche);
    }

    public void deleteGaraje(Long id) {
        LOGGER.info("Eliminando garaje con id {}", id);
        garajeRepository.deleteById(id);
    }

    public void deleteCoche(Long id) {
        LOGGER.info("Eliminando coche con id {}", id);
        cocheRepository.deleteById(id);
    }
}