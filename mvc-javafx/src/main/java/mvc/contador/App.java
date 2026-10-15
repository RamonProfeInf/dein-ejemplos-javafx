package mvc.contador;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.contador.controlador.ControladorContador;
import mvc.contador.modelo.Contador;
import mvc.contador.vista.VistaContador;

/**
 * La aplicación JavaFX. Aquí solo se CREAN las tres piezas y se conectan entre sí.
 *
 * JavaFX llama a start() desde su propio hilo de interfaz (JavaFX Application
 * Thread) y nos da la ventana principal (Stage) ya creada. Toda la interfaz se
 * crea y se modifica desde ese hilo.
 */
public class App extends Application {

    @Override
    public void start(Stage ventana) {
        Contador modelo = new Contador();
        VistaContador vista = new VistaContador();
        new ControladorContador(modelo, vista);

        // Stage = la ventana; Scene = su contenido; la vista es el nodo raíz de la escena
        ventana.setTitle("MVC - Contador");
        ventana.setScene(new Scene(vista, 300, 200));
        ventana.show();
    }
}
