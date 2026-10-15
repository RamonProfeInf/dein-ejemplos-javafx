package soluciones.conversor;

import javafx.application.Application;

/**
 * Punto de entrada. No hereda de Application para evitar el error
 * "JavaFX runtime components are missing" al ejecutarlo desde el IDE.
 */
public class Main {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}
