package es.cic.curso25.proy014;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClaseATest {

    private ClaseA claseA; // Clase que sometemos a test
    private ClaseB claseB; // Dependencia

    @BeforeEach
    public void setUp() {
        claseA = new ClaseA();
        claseB = mock(ClaseB.class);
        claseA.setClaseB(claseB);
        // El mock significa que va a inyectar un objeto simulado. De base, si no tiene
        // nada, el objeto es 0
    }

    @Test
    public void testMetodo1ClaseA() {

        when(claseB.metodo2(2, 3)).thenReturn(5);

        int resultado = claseA.metodo1(2, 3);

        assertEquals(5, resultado);
        verify(claseB, times(1)).metodo2(2, 3);
        // Verifica que claseB se ha llamado X veces, el metodo2 con esos datos
    }
}

/*
 * -------------------------------------------------- PROY015 --------------------------------------------------
 * UN GARAJE
 * SOLO UNO
 * Solo un garaje por lo que no hacemos una entidad garaje
 * 150 PLAZAS maximo
 * En principio al ser un trabajo a medida no van a aparecer 70 plazas de golpe
 * Una lista de plazas
 * una opcion para leer plazas
 * una forma de ver el ESTADO de la plaza (ocupada, libre, deshabilitada por algo)
 * no deberia tener una opcion de borrar plazas, porque no vas a quitarla, como mucho la deshabilitas
 * Tampoco se van a crear plazas en principio, no haria falta un modo crear
 * para modificar SOLO MODIFICAMOS DESCRIPCIÓN Y SI ESTÁ DISPONIBLE O NO (PEDIDO POR EL CLIENTE), DEBERIAMOS REVISAR QUE AL PONERLO EN NO DISPONIBLE NO HAYA UN COCHE YA
 * 
 * ENTIDAD: PLAZA
 * 
 * En las plazas tenemos coches aparcados
 * Registro coches y los quito
 * Que el garaje sea publico o privado no importa
 * 
 * ENTIDAD COCHE
 * 
 * El coche tiene multas (no tiene por qué ser entidad multa)
 * 
 * Para poder aparcar primero tiene que existir el coche
 * en una plaza hay como mucho un vehiculo (one to one), y un coche como mucho puede estar en una plaza (one to one)
 * Una plaza puede no tener coche, y un coche puede no estar en una plaza
 * Ya no es coche, es vehiculo (atributo tipo)
 * Un coche tiene una plaza vinculada (solo puede apacar ahí), pero varios coches pueden tener la misma plaza vinculada. 
 * Si un coche intenta aparcar en una plaza ya utilizada, no puede hacerlo, pero puede aparcar en una plaza que no es la suya. En ese caso, le multamos
 * 
 * --------------------------------------------------
 * 
 * ENTIDADES:
 * VEHICULO,
 * PLAZA,
 * MULTA
 * 
 * --------------------------------------------------
 * Garaje existe, pero no es una entidad, porque estamos en un unico garaje donde ocurre todo
 * Puedo multar múltiples veces
 * La multa se aplica en base a los días una vez se saca. Son 5e por día y son 4 días, serian 20e de multa.
 * Al ser constante, podriamos tener el valor en el application.properties
 * 
 * Todo esto es lógica de servicio, son comprobaciones y cosas que se hacen en servicio
 * 
 */