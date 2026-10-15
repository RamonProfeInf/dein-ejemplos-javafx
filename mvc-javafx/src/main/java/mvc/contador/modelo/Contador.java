package mvc.contador.modelo;

/**
 * MODELO: guarda el estado de la aplicación (el valor del contador)
 * y las reglas para cambiarlo.
 *
 * Fíjate en que esta clase NO importa nada de JavaFX: el modelo no sabe que
 * existe una ventana. Gracias a eso podríamos usarlo igual en una aplicación de
 * consola, en una web o en un test automático, y cambiar toda la interfaz sin
 * tocarlo (el ejemplo contadorfxml lo reutiliza tal cual).
 */
public class Contador {

    private int valor;

    public void incrementar() {
        valor++;
    }

    public void reiniciar() {
        valor = 0;
    }

    public int getValor() {
        return valor;
    }
}
