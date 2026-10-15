package soluciones.banco.vista;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import soluciones.banco.modelo.Movimiento;

/**
 * VISTA 2 (observadora): historial de movimientos.
 */
public class VistaMovimientos extends TitledPane {

    private final ListView<Movimiento> lista = new ListView<>();

    public VistaMovimientos() {
        super("Movimientos", null);

        // La "fábrica de celdas" decide CÓMO se pinta cada movimiento. Es cosa de
        // la vista: por eso el formato no está en Movimiento.toString().
        lista.setCellFactory(l -> new ListCell<>() {
            @Override
            protected void updateItem(Movimiento movimiento, boolean vacia) {
                super.updateItem(movimiento, vacia);
                setText(vacia || movimiento == null ? null : formatear(movimiento));
            }
        });

        setContent(lista);
        setCollapsible(false);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /** Conecta el ListView con la lista observable: cada movimiento nuevo aparece solo. */
    public void mostrar(ObservableList<Movimiento> movimientos) {
        lista.setItems(movimientos);
    }

    private static String formatear(Movimiento m) {
        String signo = m.tipo() == Movimiento.Tipo.INGRESO ? "+" : "-";
        return String.format("%-9s %s%.2f €   (saldo: %.2f €)",
                m.tipo(), signo, m.importe(), m.saldoResultante());
    }
}
