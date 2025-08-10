package es.cic.curso25.proy014.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.cic.curso25.proy014.model.Plaza;
import es.cic.curso25.proy014.repository.PlazaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class PlazaService {

    @Autowired
    private PlazaRepository plazaRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(PlazaService.class); // Por si se usa en el futuro

    public List<Plaza> getAllPlazas() {
        LOGGER.info("Obteniendo todas las plazas desde la base de datos");
        return plazaRepository.findAll();
    }

    public Optional<Plaza> getPlaza(Long id) {
        LOGGER.info(String.format("Leída la plaza con id %s", id));
        return plazaRepository.findById(id);
    }


    public Plaza createPlaza(Plaza plaza) {
        LOGGER.info("Creación/actualización de plaza");
        return plazaRepository.save(plaza);
    }

    public void deletePlaza(Long id) {
        LOGGER.info("Eliminando garaje con id {}", id);
        plazaRepository.deleteById(id);
    }


}