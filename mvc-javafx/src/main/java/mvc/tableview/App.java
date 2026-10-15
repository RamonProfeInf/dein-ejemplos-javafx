package mvc.tableview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.tableview.controlador.ControladorAlumnos;
import mvc.tableview.modelo.Alumno;
import mvc.tableview.modelo.GrupoAlumnos;
import mvc.tableview.vista.VistaAlumnos;

public class App extends Application {

    @Override
    public void start(Stage ventana) {
        GrupoAlumnos grupo = new GrupoAlumnos();
        grupo.agregar(new Alumno("Ana García", 19, 8.5));
        grupo.agregar(new Alumno("Luis Pérez", 21, 6.0));
        grupo.agregar(new Alumno("Marta Ruiz", 20, 9.25));

        VistaAlumnos vista = new VistaAlumnos();
        // La tabla y la etiqueta de la media se conectan a las partes observables del modelo
        vista.mostrar(grupo.getAlumnos());
        vista.mostrarMedia(grupo.notaMediaProperty());

        new ControladorAlumnos(grupo, vista);

        ventana.setTitle("MVC - TableView de alumnos");
        ventana.setScene(new Scene(vista, 600, 350));
        ventana.show();
    }
}
