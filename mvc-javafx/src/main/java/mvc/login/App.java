package mvc.login;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.login.controlador.ControladorLogin;
import mvc.login.modelo.UsuarioService;
import mvc.login.vista.VistaLogin;

/**
 * Usuarios de prueba: admin/1234, alumno/dam2, profe/javafx
 */
public class App extends Application {

    @Override
    public void start(Stage ventana) {
        UsuarioService servicio = new UsuarioService();
        VistaLogin vista = new VistaLogin();
        new ControladorLogin(servicio, vista);

        ventana.setTitle("MVC - Login");
        ventana.setScene(new Scene(vista));
        ventana.show();
    }
}
