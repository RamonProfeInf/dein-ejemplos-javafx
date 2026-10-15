package mvc.contadorfxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.contador.modelo.Contador;
import mvc.contadorfxml.controlador.ControladorContador;

/**
 * El mismo contador, pero con la vista escrita en FXML.
 *
 * Fíjate en el import de Contador: es el MISMO modelo del ejemplo "contador".
 * Hemos cambiado la vista por completo sin tocar ni una línea del modelo.
 */
public class App extends Application {

    @Override
    public void start(Stage ventana) throws IOException {
        Contador modelo = new Contador();

        // El FXML está en src/main/resources/mvc/contadorfxml/vista/
        FXMLLoader cargador = new FXMLLoader(App.class.getResource("vista/VistaContador.fxml"));

        // El FXML dice QUÉ clase es el controlador (fx:controller), pero el
        // FXMLLoader no sabe crearla con el modelo. Con la fábrica le decimos CÓMO.
        cargador.setControllerFactory(tipo -> new ControladorContador(modelo));

        Parent vista = cargador.load(); // crea los nodos, el controlador y los conecta

        ventana.setTitle("MVC - Contador (FXML)");
        ventana.setScene(new Scene(vista));
        ventana.show();
    }
}
