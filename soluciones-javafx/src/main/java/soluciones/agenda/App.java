package soluciones.agenda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import soluciones.agenda.controlador.ControladorAgenda;
import soluciones.agenda.modelo.Agenda;
import soluciones.agenda.modelo.Contacto;
import soluciones.agenda.vista.VistaAgenda;

public class App extends Application {

    @Override
    public void start(Stage ventana) {
        Agenda agenda = new Agenda();
        agenda.agregar(new Contacto("Ana García", "600111222", "ana@correo.es"));
        agenda.agregar(new Contacto("Luis Pérez", "611222333", ""));
        agenda.agregar(new Contacto("Marta Ruiz", "622333444", "marta@instituto.es"));

        VistaAgenda vista = new VistaAgenda();
        vista.mostrar(agenda.getResultadoBusqueda()); // la tabla observa el resultado de la búsqueda
        new ControladorAgenda(agenda, vista);

        ventana.setTitle("MVC - Agenda de contactos");
        ventana.setScene(new Scene(vista, 560, 480));
        ventana.show();
    }
}
