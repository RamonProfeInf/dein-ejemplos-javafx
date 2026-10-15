package mvc.compra.controlador;

import mvc.compra.modelo.ListaCompra;
import mvc.compra.modelo.Producto;
import mvc.compra.vista.VentanaCompra;

/**
 * CONTROLADOR de la lista de la compra.
 *
 * ¡Compáralo con ControladorContador! Aquí el controlador SOLO modifica el
 * modelo. No llama a ningún "refrescarVista()": las vistas observan al modelo
 * y se actualizan solas.
 */
public class ControladorCompra {

    private final ListaCompra modelo;
    private final VentanaCompra ventana;

    public ControladorCompra(ListaCompra modelo, VentanaCompra ventana) {
        this.modelo = modelo;
        this.ventana = ventana;
        ventana.alPulsarAgregar(() -> agregar());
        ventana.alPulsarEliminar(() -> eliminar());
    }

    private void agregar() {
        double precio;
        try {
            // Aceptamos tanto "1,50" como "1.50"
            precio = Double.parseDouble(ventana.getPrecio().trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            ventana.mostrarError("El precio debe ser un número");
            return;
        }

        try {
            modelo.agregar(new Producto(ventana.getNombre().trim(), precio));
            ventana.limpiarFormulario();
        } catch (IllegalArgumentException ex) {
            // Las validaciones del modelo llegan como excepciones; la vista solo las muestra
            ventana.mostrarError(ex.getMessage());
        }
    }

    private void eliminar() {
        int indice = ventana.getVistaProductos().getIndiceSeleccionado();
        if (indice < 0) {
            ventana.mostrarError("Selecciona un producto de la lista");
            return;
        }
        modelo.eliminar(indice);
    }
}
