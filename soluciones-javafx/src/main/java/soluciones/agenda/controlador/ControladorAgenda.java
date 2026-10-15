package soluciones.agenda.controlador;

import soluciones.agenda.modelo.Agenda;
import soluciones.agenda.modelo.Contacto;
import soluciones.agenda.vista.VistaAgenda;

/**
 * CONTROLADOR de la agenda: alta, baja, modificación y búsqueda.
 *
 * No hay ningún refrescarTabla() ni hace falta recordar el último filtro: la
 * tabla observa el resultado de la búsqueda del modelo, que se mantiene al día solo.
 */
public class ControladorAgenda {

    private final Agenda agenda;
    private final VistaAgenda vista;

    public ControladorAgenda(Agenda agenda, VistaAgenda vista) {
        this.agenda = agenda;
        this.vista = vista;

        vista.alPulsarBuscar(() -> agenda.buscar(vista.getTextoBusqueda()));
        vista.alPulsarAlta(() -> alta());
        vista.alPulsarModificar(() -> modificar());
        vista.alPulsarBaja(() -> baja());
        vista.alPulsarLimpiar(() -> vista.limpiarFormulario());
        vista.alSeleccionarFila(() -> cargarSeleccionado());
    }

    private void alta() {
        try {
            agenda.agregar(leerFormulario());
            vista.limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void modificar() {
        Contacto seleccionado = vista.getSeleccionado();
        if (seleccionado == null) {
            vista.mostrarError("Selecciona en la tabla el contacto a modificar");
            return;
        }
        try {
            agenda.modificar(seleccionado, leerFormulario());
            vista.limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            vista.mostrarError(ex.getMessage());
        }
    }

    private void baja() {
        Contacto seleccionado = vista.getSeleccionado();
        if (seleccionado == null) {
            vista.mostrarError("Selecciona en la tabla el contacto a eliminar");
            return;
        }
        if (vista.confirmar("¿Eliminar a " + seleccionado.nombre() + "?")) {
            agenda.eliminar(seleccionado);
            vista.limpiarFormulario();
        }
    }

    /** Al seleccionar una fila, copiamos sus datos al formulario para poder editarlos. */
    private void cargarSeleccionado() {
        Contacto c = vista.getSeleccionado();
        if (c != null) {
            vista.rellenarFormulario(c.nombre(), c.telefono(), c.email());
        }
    }

    private Contacto leerFormulario() {
        return new Contacto(vista.getNombre().trim(), vista.getTelefono().trim(), vista.getEmail().trim());
    }
}
