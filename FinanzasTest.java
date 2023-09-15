import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FinanzasTest {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testObtenerNombreProducto() {
        String input = "Leche de platano";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String nombre = obtenerNombreProducto();

        assertEquals("Leche de platano", nombre.trim());
    }

    private String obtenerNombreProducto() {
        System.out.print("Ingresa el nombre del producto: ");
        return new java.util.Scanner(System.in).nextLine();
    }

    @Test
    public void testAnadirDineroNumeroCero() {
        InputStream originalSystemIn = System.in;
        PrintStream originalSystemOut = System.out;

        try {
            ByteArrayInputStream inContent = new ByteArrayInputStream("0.0\n".getBytes());
            System.setIn(inContent);

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Finanzas.anadirDinero();

            assertEquals(0.0, Finanzas.saldoActual, 0.01);
        } finally {
            System.setIn(originalSystemIn);
            System.setOut(originalSystemOut);
        }
    }
}

