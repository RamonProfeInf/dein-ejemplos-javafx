package mvc.compra.vista;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * VISTA 2: una etiqueta con el total.
 *
 * Es otro observador independiente del mismo modelo. No sabe nada de la
 * VistaProductos: cada vista escucha al modelo por su cuenta.
 */
public class VistaTotal extends HBox {

    private final Label lblTotal = new Label();

    public VistaTotal() {
        lblTotal.setFont(Font.font("System", FontWeight.BOLD, 18));
        getChildren().add(lblTotal);
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(8));
    }

    /**
     * ENLAZA (bind) la etiqueta con el total del modelo.
     *
     * bind() significa: "el texto de esta etiqueta es SIEMPRE el total con este
     * formato". Cuando el total cambia, el texto cambia solo. Por eso esta vista
     * no necesita ningún método refrescar().
     */
    public void mostrar(ReadOnlyDoubleProperty total) {
        lblTotal.textProperty().bind(total.asString("Total: %.2f €"));
    }
}
