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
