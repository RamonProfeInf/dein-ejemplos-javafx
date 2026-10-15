package soluciones.banco.controlador;

import java.util.function.DoubleConsumer;
import soluciones.banco.modelo.CuentaBancaria;
import soluciones.banco.vista.VentanaBanco;

/**
 * CONTROLADOR: traduce los clics en operaciones sobre la cuenta.
 * No refresca ninguna vista: las vistas observan las propiedades del modelo.
 */
public class ControladorBanco {

    private final VentanaBanco ventana;

    public ControladorBanco(CuentaBancaria cuenta, VentanaBanco ventana) {
        this.ventana = ventana;
        // Pasamos la operación como referencia a método (cuenta::ingresar)
        // para no repetir dos veces el mismo código de lectura y errores.
        ventana.alPulsarIngresar(() -> operar(cuenta::ingresar));
        ventana.alPulsarRetirar(() -> operar(cuenta::retirar));
    }

    private void operar(DoubleConsumer operacion) {
        try {
            double importe = Double.parseDouble(ventana.getImporte().trim().replace(',', '.'));
            operacion.accept(importe);
            ventana.limpiarImporte();
        } catch (NumberFormatException ex) {
            ventana.mostrarError("El importe debe ser un número");
        } catch (RuntimeException ex) {
            // IllegalArgumentException (importe <= 0) o SaldoInsuficienteException
            ventana.mostrarError(ex.getMessage());
        }
    }
}
