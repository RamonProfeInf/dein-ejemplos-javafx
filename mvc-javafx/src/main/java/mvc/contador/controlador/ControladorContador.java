package mvc.contador.controlador;

import mvc.contador.modelo.Contador;
import mvc.contador.vista.VistaContador;

/**
 * CONTROLADOR: es el "pegamento" entre la vista y el modelo.
 *
 * Cada vez que el usuario hace algo, el controlador sigue siempre los mismos pasos:
 *   1) pide al MODELO que cambie,
 *   2) lee el nuevo estado del modelo y se lo pasa a la VISTA.
 *
 * Los enganches de la vista reciben un Runnable, por eso las lambdas no llevan
 * parámetros: "() -> incrementar()" significa "cuando lo pulsen, llama a incrementar()".
 *
 * En este ejemplo la vista NO se entera sola de los cambios: es el controlador
 * quien la refresca. En el ejemplo "compra" veremos cómo hacer que las vistas
 * se actualicen solas con las propiedades observables de JavaFX.
 */
public class ControladorContador {

    private final Contador modelo;
    private final VistaContador vista;

    public ControladorContador(Contador modelo, VistaContador vista) {
        this.modelo = modelo;
        this.vista = vista;

        // Nos suscribimos a los botones de la vista
        vista.alPulsarIncrementar(() -> incrementar());
        vista.alPulsarReset(() -> reiniciar());

        refrescarVista(); // mostrar el estado inicial
    }

    private void incrementar() {
        modelo.incrementar(); // 1) cambiar el modelo
        refrescarVista();     // 2) actualizar la vista
    }

    private void reiniciar() {
        modelo.reiniciar();
        refrescarVista();
    }

    private void refrescarVista() {
        vista.mostrar(modelo.getValor());
    }
}
