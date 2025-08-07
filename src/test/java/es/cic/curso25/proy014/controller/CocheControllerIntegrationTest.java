package es.cic.curso25.proy014.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.curso25.proy014.model.Coche;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.model.Multa;
import es.cic.curso25.proy014.repository.CocheRepository;
import es.cic.curso25.proy014.repository.GarajeRepository;
import es.cic.curso25.proy014.repository.MultaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@AutoConfigureMockMvc
public class CocheControllerIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CocheControllerIntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CocheRepository cocheRepository;

    @Autowired
    private GarajeRepository garajeRepository;

    @Autowired
    private MultaRepository multaRepository;

    @BeforeEach
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void resetDatabase() {
        // Con @DirtiesContext, Spring reiniciará el contexto y recargará data.sql antes de cada test
    }

    @Test
    void testLeerGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testLeerGaraje");
        LOGGER.info("Leyendo garaje con id 1");

        mockMvc.perform(get("/garajes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        LOGGER.info("[TEST] Finalizado testLeerGaraje");
    }

    @Test
    void testCrearGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testCrearGaraje");
        LOGGER.info("Creando objeto Garaje");

        Garaje garaje = new Garaje();

        garaje.setDireccion("Nueva Calle 123");
        garaje.setCapacidadMaxima(5L);
        garaje.setTelefono("600333333");
        garaje.setPropietario("Carlos Test");

        LOGGER.info("Enviando POST para crear garaje: " + garaje.toString());

        mockMvc.perform(post("/garajes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccion").value("Nueva Calle 123"));

        LOGGER.info("[TEST] Finalizado testCrearGaraje");
    }

    @Test
    void testModificarGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testModificarGaraje");
        LOGGER.info("Leyendo garaje con id 1");

        Optional<Garaje> garajeExistente = garajeRepository.findById(1L);
        Long idGaraje;
        Garaje garaje = new Garaje();

        garaje.setDireccion("Calle Modificada");
        garaje.setCapacidadMaxima(7L);
        garaje.setTelefono("600444444");

        garaje.setPropietario("Modificado");
        if (garajeExistente.isPresent())
            idGaraje = garajeExistente.get().getId();
        else
            throw new SecurityException("Error: no se encontró el garaje con ese id");

        LOGGER.info("Enviando PUT para modificar garaje");

        mockMvc.perform(put("/garajes/{id}", idGaraje)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccion").value("Calle Modificada"));

        LOGGER.info("[TEST] Finalizado testModificarGaraje");
    }

    @Test
    void testBorrarGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testBorrarGaraje");
        LOGGER.info("Enviando DELETE para borrar garaje con id 1");

        mockMvc.perform(delete("/garajes/1"))
                .andExpect(resultado -> {
                    Optional<Garaje> garaje = garajeRepository.findById(1L);
                    assertTrue(garaje.isEmpty(), "Error: no se ha borrado correctamente el garaje");
                });

        LOGGER.info("[TEST] Finalizado testBorrarGaraje");
    }



    @Test
    void testModificarCoche() throws Exception {

        LOGGER.info("[TEST] Iniciando testModificarCoche");
        LOGGER.info("Leyendo garaje con id 1");

        Optional<Garaje> garajeExistente = garajeRepository.findById(1L);
        Garaje garaje = garajeExistente.get();

        LOGGER.info("Buscando coche con id 2 en el garaje");

        Coche cocheExistente = garaje.getCoches().stream()
                .filter(coche -> coche.getId().equals(2L))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró el coche con el id 2"));

        LOGGER.info("Modificando datos del coche");

        cocheExistente.setMatricula("1234ABC");
        cocheExistente.setModelo("Toyota testing");

        LOGGER.info("Enviando PUT para actualizar el garaje");

        mockMvc.perform(put("/garajes/{id}", garaje.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(resultado -> {
                    String jsonResponse = resultado.getResponse().getContentAsString();
                    Garaje garajeVerificado = objectMapper.readValue(jsonResponse, Garaje.class);
                    Coche cocheModificadoRecuperado = garajeVerificado.getCoches().stream()
                            .filter(coche -> coche.getId().equals(2L))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "No se encontró el coche con el id 2"));
                    assertEquals("1234ABC", cocheModificadoRecuperado.getMatricula());
                    assertEquals("Toyota testing", cocheModificadoRecuperado.getModelo());
                });

        LOGGER.info("Verificando en base de datos");

        Coche verificoCoche = cocheRepository.findById(2L).get();
        assertEquals("1234ABC", verificoCoche.getMatricula());
        assertEquals("Toyota testing", verificoCoche.getModelo());

        LOGGER.info("[TEST] Finalizado testModificarCoche");
    }

    @Test
    void testCrearCocheEnGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testCrearCocheEnGaraje");
        LOGGER.info("Leyendo garaje con id 2");

        Garaje garaje = garajeRepository.findById(2L).orElseThrow(() -> new RuntimeException("No existe el garaje 2"));
        
        LOGGER.info("Creando nuevo coche para añadir al garaje "+ garaje.toString());
        Coche coche = new Coche();

        coche.setMatricula("TEST1234");
        coche.setModelo("Test Modelo");
        coche.setColor("Azul");
        coche.setGaraje(garaje);

        LOGGER.info("Añadiendo coche a la lista de coches del garaje");

        garaje.getCoches().add(coche);

        LOGGER.info("Enviando PUT para actualizar el garaje con el nuevo coche");

        mockMvc.perform(put("/garajes/{id}", garaje.getId())

                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coches[-1:].matricula").value("TEST1234"))
                .andExpect(jsonPath("$.coches[-1:].modelo").value("Test Modelo"))
                .andExpect(jsonPath("$.coches[-1:].color").value("Azul"));

        LOGGER.info("Verificando que el coche se ha guardado correctamente en la base de datos");

        boolean existe = cocheRepository.findAll().stream()
                .anyMatch(cocheTest -> "TEST1234".equals(cocheTest.getMatricula()) && cocheTest.getGaraje().getId().equals(2L));
        
            assertTrue(existe, "El coche no se ha guardado correctamente en el garaje 2");

        LOGGER.info("[TEST] Finalizado testCrearCocheEnGaraje");
    }

    @Test
    void testAgregarMultaACocheDesdeGaraje() throws Exception {

        LOGGER.info("[TEST] Iniciando testAgregarMultaACocheDesdeGaraje");
        LOGGER.info("Leyendo garaje con id 1");

        Garaje garaje = garajeRepository.findById(1L).orElseThrow(() -> new RuntimeException("No existe el garaje 1"));
       
        LOGGER.info("Buscando coche con numPlaza 1 en el garaje");
        
        Coche coche = garaje.getCoches().stream()
                .filter(c -> c.getNumPlaza().equals(1L))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe el coche 1 en el garaje 1"));
        
        LOGGER.info("Creando nueva multa y añadiéndola al coche");
        
        Multa nuevaMulta = new Multa();
        nuevaMulta.setRazonMulta("Multa test integración");
        nuevaMulta.setImporte(99.99);
        nuevaMulta.setFecha("2025-08-07");
        nuevaMulta.setCoche(coche);
        coche.getMultas().add(nuevaMulta);

        LOGGER.info("Enviando PUT para actualizar el garaje con la nueva multa: " + nuevaMulta.toString());

        mockMvc.perform(put("/garajes/{id}", garaje.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coches[0].multas[-1:].razonMulta").value("Multa test integración"));
        LOGGER.info("Verificando en base de datos que la multa se ha guardado correctamente");

        Coche cocheActualizado = cocheRepository.findById(1L).orElseThrow();
        boolean multaEnCoche = cocheActualizado.getMultas().stream()
                              .anyMatch(m -> "Multa test integración".equals(m.getRazonMulta()));

        assertTrue(multaEnCoche, "La multa no se ha guardado correctamente en el coche");

        LOGGER.info("[TEST] Finalizado testAgregarMultaACocheDesdeGaraje");
    }

    @Test
    void testModificarMulta() throws Exception {

        LOGGER.info("[TEST] Iniciando testModificarMulta");
        LOGGER.info("Leyendo garaje con id 1");

        Garaje garaje = garajeRepository.findById(1L).orElseThrow(() -> new RuntimeException("No existe el garaje 1"));
        
        LOGGER.info("Buscando coche con numPlaza 1 en el garaje");

        Coche coche = garaje.getCoches().stream()
                .filter(c -> c.getNumPlaza().equals(1L))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe el coche 1 en el garaje 1"));
        
        LOGGER.info("Buscando primera multa del coche");

        Multa multa = coche.getMultas().get(0);

        LOGGER.info("Modificando la multa");
        
        multa.setRazonMulta("Multa modificada");
        multa.setImporte(123.45);

        LOGGER.info("Enviando PUT para actualizar el garaje con la multa modificada");

        mockMvc.perform(put("/garajes/{id}", garaje.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(resultado -> {
                    String jsonResponse = resultado.getResponse().getContentAsString();
                    Garaje garajeVerificado = objectMapper.readValue(jsonResponse, Garaje.class);
                    Multa multaVerificada = garajeVerificado.getCoches().get(0).getMultas().get(0);
                    assertEquals("Multa modificada", multaVerificada.getRazonMulta());
                    assertEquals(123.45, multaVerificada.getImporte());
                });

        LOGGER.info("Verificando en base de datos la multa modificada");

        Multa multaActualizada = multaRepository.findAll().stream()
                .filter(m -> m.getCoche().getNumPlaza().equals(1L))
                .findFirst()
                .orElseThrow();
        assertEquals("Multa modificada", multaActualizada.getRazonMulta());
        assertEquals(123.45, multaActualizada.getImporte());

        LOGGER.info("[TEST] Finalizado testModificarMulta");
    }

    @Test
    void testCrearCocheEnGarajeComoCrearMulta() throws Exception {
        
        LOGGER.info("[TEST] Iniciando testCrearCocheEnGarajeComoCrearMulta");
        LOGGER.info("Leyendo garaje con id 1");

        
        Garaje garaje = garajeRepository.findById(1L).orElseThrow(() -> new RuntimeException("No existe el garaje 1"));
        
        LOGGER.info("Creando nuevo coche para añadir al garaje " + garaje.toString());

        Coche coche = new Coche();

        coche.setMatricula("NUEVO123");
        coche.setModelo("Nuevo Modelo");
        coche.setColor("Verde");
        coche.setGaraje(garaje);

        LOGGER.info("Añadiendo coche a la lista de coches del garaje");

        garaje.getCoches().add(coche);

        LOGGER.info("Enviando PUT para actualizar el garaje con el nuevo coche");

        mockMvc.perform(put("/garajes/{id}", garaje.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.coches[-1:].matricula").value("NUEVO123"))
                .andExpect(jsonPath("$.coches[-1:].modelo").value("Nuevo Modelo"))
                .andExpect(jsonPath("$.coches[-1:].color").value("Verde"));

        LOGGER.info("Verificando que el coche se ha guardado correctamente en la base de datos");

        boolean existe = cocheRepository.findAll().stream()
                .anyMatch(cocheTest -> "NUEVO123".equals(cocheTest.getMatricula()) && cocheTest.getGaraje().getId().equals(1L));
       
        assertTrue(existe, "El coche no se ha guardado correctamente en el garaje 1");
        
        LOGGER.info("[TEST] Finalizado testCrearCocheEnGarajeComoCrearMulta");
    }

    @Test
    void testModificarMultaEnCoche() throws Exception {

        LOGGER.info("[TEST] Iniciando testModificarMultaEnCoche");
        LOGGER.info("Leyendo garaje con id 2");

        Garaje garaje = garajeRepository.findById(2L).orElseThrow(() -> new RuntimeException("No existe el garaje 2"));
        
        LOGGER.info("Buscando coche con numPlaza 8 en el garaje");
        
        Coche coche = garaje.getCoches().stream()
                .filter(c -> c.getNumPlaza().equals(8L))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No existe el coche 8 en el garaje 2"));
        
        LOGGER.info("Buscando multa 'Multa grave' en el coche");
        
                
        Multa multa = coche.getMultas().stream()
                .filter(m -> "Multa grave".equals(m.getRazonMulta()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay multa 'Multa grave' en el coche 8"));
                
        LOGGER.info("Modificando la multa");

        multa.setRazonMulta("Multa modificada garaje 2");
        multa.setImporte(222.22);

        LOGGER.info("Enviando PUT para actualizar el garaje con la multa modificada");

        mockMvc.perform(put("/garajes/{id}", garaje.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(resultado -> {

                    String jsonResponse = resultado.getResponse().getContentAsString();
                    Garaje garajeVerificado = objectMapper.readValue(jsonResponse, Garaje.class);
                    
                    Coche cocheVerificado = garajeVerificado.getCoches().stream()
                        .filter(c -> c.getNumPlaza().equals(8L))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró el coche 8 en el garaje 2 (respuesta)"));
                    
                    Multa multaVerificada = cocheVerificado.getMultas().stream()
                        .filter(m -> "Multa modificada garaje 2".equals(m.getRazonMulta()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró la multa modificada en el coche 8 (respuesta)"));
                    assertEquals("Multa modificada garaje 2", multaVerificada.getRazonMulta());
                    assertEquals(222.22, multaVerificada.getImporte());
                });

        LOGGER.info("Verificando en base de datos la multa modificada");


        Multa multaActualizada = multaRepository.findAll().stream()
                .filter(m -> m.getCoche().getNumPlaza().equals(8L) && "Multa modificada garaje 2".equals(m.getRazonMulta()))
                .findFirst()
                .orElseThrow();
        assertEquals("Multa modificada garaje 2", multaActualizada.getRazonMulta());
        assertEquals(222.22, multaActualizada.getImporte());

        LOGGER.info("[TEST] Finalizado testModificarMultaEnCoche");
    }

    
}
