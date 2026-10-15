package soluciones.banco.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests del MODELO. Fíjate en que no se abre ninguna ventana: las propiedades
 * y listas observables de JavaFX funcionan sin interfaz, así que el modelo se
 * puede probar de forma rápida y automática.
 */
class CuentaBancariaTest {

    private static final double DELTA = 0.001; // margen al comparar doubles

    private CuentaBancaria cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria("Ana");
        cuenta.ingresar(100);
    }

    @Test
    void ingresarAumentaElSaldo() {
        cuenta.ingresar(50);

        assertEquals(150, cuenta.getSaldo(), DELTA);
    }

    @Test
    void retirarMasDelSaldoLanzaExcepcionYNoCambiaElSaldo() {
        assertThrows(SaldoInsuficienteException.class, () -> cuenta.retirar(500));

        assertEquals(100, cuenta.getSaldo(), DELTA);
        assertEquals(1, cuenta.getMovimientos().size()); // solo el ingreso inicial
    }

    @Test
    void retirarNotificaElCambioDeSaldoALosObservadores() {
        List<Number> valores = new ArrayList<>(); // [antes, después]
        cuenta.saldoProperty().addListener((propiedad, antes, despues) -> {
            valores.add(antes);
            valores.add(despues);
        });

        cuenta.retirar(30);

        assertEquals(2, valores.size());
        assertEquals(100.0, valores.get(0).doubleValue(), DELTA);
        assertEquals(70.0, valores.get(1).doubleValue(), DELTA);
    }

    @Test
    void elSaldoEsBajoSoloPorDebajoDelLimite() {
        assertFalse(cuenta.isSaldoBajo()); // 100 € justos: todavía no es bajo

        cuenta.retirar(0.01);

        assertTrue(cuenta.isSaldoBajo());
    }
}
