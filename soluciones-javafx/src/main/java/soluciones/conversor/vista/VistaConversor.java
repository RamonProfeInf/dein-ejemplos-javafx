package soluciones.conversor.vista;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * VISTA: dos campos (ºC y ºF) y dos botones, uno para cada sentido.
 */
public class VistaConversor extends GridPane {

    private final TextField txtCelsius = new TextField();
    private final TextField txtFahrenheit = new TextField();
    private final Button btnCaF = new Button("ºC → ºF");
    private final Button btnFaC = new Button("ºF → ºC");

    public VistaConversor() {
        txtCelsius.setPrefColumnCount(8);
        txtFahrenheit.setPrefColumnCount(8);
        btnCaF.setMaxWidth(Double.MAX_VALUE);
        btnFaC.setMaxWidth(Double.MAX_VALUE);

        addRow(0, new Label("Celsius:"), txtCelsius, btnCaF);
        addRow(1, new Label("Fahrenheit:"), txtFahrenheit, btnFaC);
        setHgap(5);
        setVgap(5);
        setPadding(new Insets(10));
    }

    public String getCelsius() {
        return txtCelsius.getText();
    }

    public String getFahrenheit() {
        return txtFahrenheit.getText();
    }

    public void mostrarCelsius(double celsius) {
        txtCelsius.setText(String.format("%.2f", celsius));
    }

    public void mostrarFahrenheit(double fahrenheit) {
        txtFahrenheit.setText(String.format("%.2f", fahrenheit));
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow());
        alerta.showAndWait();
    }

    public void alPulsarCelsiusAFahrenheit(Runnable accion) {
        btnCaF.setOnAction(e -> accion.run());
    }

    public void alPulsarFahrenheitACelsius(Runnable accion) {
        btnFaC.setOnAction(e -> accion.run());
    }
}
