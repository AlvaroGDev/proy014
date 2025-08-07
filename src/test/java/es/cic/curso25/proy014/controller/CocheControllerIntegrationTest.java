package es.cic.curso25.proy014.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.cic.curso25.proy014.model.Garaje;
import es.cic.curso25.proy014.repository.GarajeRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
public class CocheControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GarajeRepository garajeRepository;

    @Test
    void testLeerGaraje() throws Exception {
        mockMvc.perform(get("/garajes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testCrearGaraje() throws Exception {
        Garaje garaje = new Garaje();
        garaje.setDireccion("Nueva Calle 123");
        garaje.setCapacidadMaxima(5L);
        garaje.setTelefono("600333333");
        garaje.setPropietario("Carlos Test");

        mockMvc.perform(post("/garajes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccion").value("Nueva Calle 123"));
    }

    @Test
    void testModificarGaraje() throws Exception {

        Optional<Garaje> garajeExistente = garajeRepository.findById(55L);
        Long idGaraje;

        Garaje garaje = new Garaje();
        garaje.setDireccion("Calle Modificada");
        garaje.setCapacidadMaxima(7L);
        garaje.setTelefono("600444444");
        garaje.setPropietario("Modificado");

        if (garajeExistente.isPresent()) 
             idGaraje = garajeExistente.get().getId();
             else
             throw new IllegalStateException("error");

        mockMvc.perform(put("/garajes/{id}", idGaraje ) //Apunta directamente aquí porque ya hay un garaje con ID 1
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(garaje)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.direccion").value("Calle Modificada"));
    }

    @Test
    void testBorrarGaraje() throws Exception {
        mockMvc.perform(delete("/garajes/1"))
                .andExpect(status().isNoContent())
                .andExpect(resultado -> {
                    Optional<Garaje> garaje = garajeRepository.findById(1L);
                    assertTrue(garaje.isEmpty(), "Error: no se ha borrado correctamente el garaje");
                });
    }
}
