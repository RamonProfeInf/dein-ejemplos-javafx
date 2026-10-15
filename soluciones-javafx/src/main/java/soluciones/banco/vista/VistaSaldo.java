package soluciones.banco.vista;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * VISTA 1 (observadora): muestra el saldo actual en grande, en rojo si es bajo.
 */
public class VistaSaldo extends TitledPane {

    private final Label lblSaldo = new Label();

    public VistaSaldo() {
        super("Saldo", null);
        lblSaldo.setFont(Font.font("System", FontWeight.BOLD, 28));
        setContent(new StackPane(lblSaldo)); // StackPane centra la etiqueta
        setCollapsible(false);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * Enlaza la etiqueta con el saldo:
     *   - el texto es siempre el saldo con formato,
     *   - el color es rojo mientras "saldo bajo" sea verdadero.
     *
     * Reparto de responsabilidades: QUÉ es un saldo bajo (menos de 100 €) lo
     * decide el modelo, porque es una regla de negocio. Que eso se pinte en ROJO
     * lo decide la vista, porque es aspecto.
     */
    public void mostrar(ReadOnlyDoubleProperty saldo, ReadOnlyBooleanProperty saldoBajo) {
        lblSaldo.textProperty().bind(saldo.asString("%.2f €"));
        lblSaldo.textFillProperty().bind(
                Bindings.when(saldoBajo).then(Color.FIREBRICK).otherwise(Color.BLACK));
    }
}
