package soluciones.banco.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

/**
 * Ventana principal: formulario de operaciones + las dos vistas observadoras.
 */
public class VentanaBanco extends BorderPane {

    private final TextField txtImporte = new TextField();
    private final Button btnIngresar = new Button("Ingresar");
    private final Button btnRetirar = new Button("Retirar");

    private final VistaSaldo vistaSaldo = new VistaSaldo();
    private final VistaMovimientos vistaMovimientos = new VistaMovimientos();

    public VentanaBanco() {
        txtImporte.setPrefColumnCount(8);
        HBox operaciones = new HBox(5, new Label("Importe:"), txtImporte, btnIngresar, btnRetirar);
        operaciones.setAlignment(Pos.CENTER_LEFT);
        operaciones.setPadding(new Insets(8));

        // Dos columnas del mismo ancho (50 % cada una) que ocupan todo el alto
        GridPane vistas = new GridPane();
        vistas.setHgap(5);
        vistas.setPadding(new Insets(0, 8, 8, 8));
        vistas.getColumnConstraints().addAll(mitad(), mitad());
        RowConstraints fila = new RowConstraints();
        fila.setVgrow(Priority.ALWAYS);
        vistas.getRowConstraints().add(fila);
        vistas.addRow(0, vistaSaldo, vistaMovimientos);

        setTop(operaciones);
        setCenter(vistas);
    }

    private static ColumnConstraints mitad() {
        ColumnConstraints columna = new ColumnConstraints();
        columna.setPercentWidth(50);
        return columna;
    }

    public VistaSaldo getVistaSaldo() {
        return vistaSaldo;
    }

    public VistaMovimientos getVistaMovimientos() {
        return vistaMovimientos;
    }

    public String getImporte() {
        return txtImporte.getText();
    }

    public void limpiarImporte() {
        txtImporte.clear();
        txtImporte.requestFocus();
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow());
        alerta.showAndWait();
    }

    public void alPulsarIngresar(Runnable accion) {
        btnIngresar.setOnAction(e -> accion.run());
    }

    public void alPulsarRetirar(Runnable accion) {
        btnRetirar.setOnAction(e -> accion.run());
    }
}
