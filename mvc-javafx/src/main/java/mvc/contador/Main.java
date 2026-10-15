package mvc.contador;

import javafx.application.Application;

/**
 * Punto de entrada.
 *
 * ¿Por qué hay un Main y además un App? Si la clase que tiene el main() hereda
 * de Application y se ejecuta desde el IDE sin configurar nada, Java se niega a
 * arrancar con el error "JavaFX runtime components are missing". Con un Main
 * que NO hereda de Application ese problema desaparece. Es un truco habitual.
 *
 * Application.launch() arranca JavaFX, crea su hilo de interfaz y llama a
 * App.start() desde ese hilo.
 */
public class Main {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
