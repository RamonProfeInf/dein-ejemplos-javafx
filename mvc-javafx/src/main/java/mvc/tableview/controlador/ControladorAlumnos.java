package mvc.tableview.controlador;

import mvc.tableview.modelo.Alumno;
import mvc.tableview.modelo.GrupoAlumnos;
import mvc.tableview.vista.VistaAlumnos;

/**
 * CONTROLADOR: lee el formulario y añade alumnos al modelo.
 *
 * Fíjate en que no refresca nada después de cada alta: la tabla y la etiqueta
 * de la media observan al modelo y se actualizan solas.
 */
public class ControladorAlumnos {

    private final GrupoAlumnos grupo;
    private final VistaAlumnos vista;

    public ControladorAlumnos(GrupoAlumnos grupo, VistaAlumnos vista) {
        this.grupo = grupo;
        this.vista = vista;
        vista.alPulsarAgregar(() -> agregar());
    }

    private void agregar() {
        try {
            int edad = Integer.parseInt(vista.getEdad().trim());
            double nota = Double.parseDouble(vista.getNota().trim().replace(',', '.'));
            Alumno alumno = new Alumno(vista.getNombre().trim(), edad, nota);

            grupo.agregar(alumno); // la tabla y la media se enteran solas
            vista.limpiarFormulario();
        } catch (NumberFormatException ex) {
            vista.mostrarError("La edad y la nota deben ser números");
        } catch (IllegalArgumentException ex) {
            // OJO: NumberFormatException es hija de IllegalArgumentException,
            // por eso su catch tiene que ir ANTES que este.
            vista.mostrarError(ex.getMessage());
        }
    }
}
