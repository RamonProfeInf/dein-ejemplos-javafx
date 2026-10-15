package mvc.compra.vista;

import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import mvc.compra.modelo.Producto;

/**
 * VISTA 1: la lista de productos (ListView).
 *
 * Basta con darle al ListView una lista observable: él mismo se suscribe a ella
 * y se repinta cada vez que cambia. Es el patrón Observer, ya hecho por JavaFX:
 * esta vista no tiene ningún método refrescar().
 */
public class VistaProductos extends TitledPane {

    private final ListView<Producto> lista = new ListView<>();

    public VistaProductos() {
        super("Productos", null);
        setContent(lista);
        setCollapsible(false);
        setMaxHeight(Double.MAX_VALUE); // que crezca si la ventana se hace más alta
    }

    /** Conecta el ListView con la lista observable. A partir de aquí se actualiza solo. */
    public void mostrar(ObservableList<Producto> productos) {
        lista.setItems(productos);
    }

    /** @return la posición seleccionada o -1 si no hay ninguna. */
    public int getIndiceSeleccionado() {
        return lista.getSelectionModel().getSelectedIndex();
    }
}
