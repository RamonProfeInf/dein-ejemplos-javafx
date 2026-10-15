package mvc.compra.modelo;

import java.util.Objects;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * MODELO OBSERVABLE (patrón Observer) con las herramientas de JavaFX.
 *
 * Problema: si varias vistas muestran los mismos datos, el controlador tendría
 * que acordarse de refrescarlas TODAS cada vez que algo cambia.
 *
 * Solución: que el modelo avise él mismo de sus cambios. JavaFX trae colecciones
 * y propiedades que YA son observables: cada vez que cambian, avisan solas a
 * quien las esté escuchando, y las vistas se actualizan solas.
 *
 *   - ObservableList: una lista que avisa cuando se añade o se quita algo.
 *   - ReadOnlyDoubleWrapper: un double que avisa cuando cambia su valor.
 *
 * ¿Puede el modelo importar JavaFX? Solo javafx.beans y javafx.collections,
 * que NO son gráficos: no hay ventanas, botones ni colores. Lo que el modelo
 * nunca importa es javafx.scene ni javafx.stage. Así sigue pudiéndose probar
 * con JUnit sin abrir ninguna ventana.
 */
public class ListaCompra {

    private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    // "Wrapper": desde dentro podemos cambiarlo; hacia fuera lo damos de solo lectura
    private final ReadOnlyDoubleWrapper total = new ReadOnlyDoubleWrapper(0);

    public ListaCompra() {
        // Cada vez que la lista cambia, se recalcula el total.
        // Es el propio modelo el que mantiene coherentes sus datos.
        productos.addListener((ListChangeListener<Producto>) cambio -> total.set(calcularTotal()));
    }

    // ---------- Operaciones que modifican el estado ----------
    // Ya no hay ningún "notificarCambios()": la ObservableList avisa sola.

    public void agregar(Producto producto) {
        productos.add(Objects.requireNonNull(producto, "El producto no puede ser null"));
    }

    public void eliminar(int indice) {
        if (indice < 0 || indice >= productos.size()) {
            throw new IndexOutOfBoundsException("No existe el producto " + indice);
        }
        productos.remove(indice);
    }

    // ---------- Consultas ----------

    /**
     * Devuelve la lista en modo SOLO LECTURA, pero observable: quien la reciba
     * (por ejemplo, un ListView) se entera de cada cambio, pero no puede
     * modificarla sin pasar por agregar() o eliminar().
     */
    public ObservableList<Producto> getProductos() {
        return FXCollections.unmodifiableObservableList(productos);
    }

    public double getTotal() {
        return total.get();
    }

    /** El total como propiedad observable, para que las vistas puedan enlazarse a él. */
    public ReadOnlyDoubleProperty totalProperty() {
        return total.getReadOnlyProperty();
    }

    private double calcularTotal() {
        double suma = 0;
        for (Producto p : productos) {
            suma += p.precio();
        }
        return suma;
    }
}
