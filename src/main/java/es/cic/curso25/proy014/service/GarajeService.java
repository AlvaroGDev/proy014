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
        /*
         * IMPORTANTE: Gestión de relaciones bidireccionales
         * -------------------------------------------------
         * Antes de guardar un garaje (y sus coches/multas),
         * es imprescindible asegurar que:
         *   - Cada coche de la lista tiene correctamente asignado su garaje (coche.setGaraje(garaje))
         *   - Cada multa de cada coche tiene correctamente asignado su coche (multa.setCoche(coche))
         * Esto es necesario porque:
         *   - Al recibir el JSON desde el cliente (o el test), las referencias pueden no estar bien enlazadas,
         *     especialmente tras la deserialización con Jackson.
         *   - JPA/Hibernate necesita que ambas partes de la relación estén sincronizadas para evitar errores de integridad,
         *     duplicados o inconsistencias al guardar (por ejemplo, errores de clave primaria o listas vacías).
         *   - Si solo se hace en el test o en el controlador, no se garantiza la consistencia en todos los casos de uso.
         * Por eso, la lógica debe estar aquí, en el service, antes de guardar.
         */
        LOGGER.info("Creación/actualización de garaje");
        // Asegura la relación bidireccional Garaje-Coche y Coche-Multa
        if (garaje.getCoches() != null) {
            garaje.getCoches().forEach(coche -> {
                coche.setGaraje(garaje);
                if (coche.getMultas() != null) {
                    coche.getMultas().forEach(multa -> multa.setCoche(coche));
                }
            });
        }
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