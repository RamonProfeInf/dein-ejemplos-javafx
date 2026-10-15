package soluciones.conversor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import soluciones.conversor.controlador.ControladorConversor;
import soluciones.conversor.modelo.ConversorTemperatura;
import soluciones.conversor.vista.VistaConversor;

public class App extends Application {

    @Override
    public void start(Stage ventana) {
        ConversorTemperatura modelo = new ConversorTemperatura();
        VistaConversor vista = new VistaConversor();
        new ControladorConversor(modelo, vista);

        ventana.setTitle("MVC - Conversor de temperatura");
        ventana.setScene(new Scene(vista));
        ventana.show();
    }
}
