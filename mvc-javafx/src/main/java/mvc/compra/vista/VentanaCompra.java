package mvc.compra.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Ventana principal: un formulario para añadir productos y, debajo,
 * las DOS vistas observadoras (lista de productos y total).
 */
public class VentanaCompra extends BorderPane {

    private final TextField txtNombre = new TextField();
    private final TextField txtPrecio = new TextField();
    private final Button btnAgregar = new Button("Añadir");
    private final Button btnEliminar = new Button("Eliminar seleccionado");

    private final VistaProductos vistaProductos = new VistaProductos();
    private final VistaTotal vistaTotal = new VistaTotal();

    public VentanaCompra() {
        txtNombre.setPrefColumnCount(10);
        txtPrecio.setPrefColumnCount(5);

        HBox formulario = new HBox(5,
                new Label("Producto:"), txtNombre, new Label("Precio:"), txtPrecio, btnAgregar);
        formulario.setAlignment(Pos.CENTER_LEFT);
        formulario.setPadding(new Insets(8));

        BorderPane sur = new BorderPane();
        sur.setLeft(btnEliminar);
        sur.setRight(vistaTotal);
        BorderPane.setAlignment(btnEliminar, Pos.CENTER_LEFT);
        sur.setPadding(new Insets(0, 0, 0, 8));

        setTop(formulario);
        setCenter(vistaProductos);
        setBottom(sur);
        BorderPane.setMargin(vistaProductos, new Insets(0, 8, 0, 8));

        btnAgregar.setDefaultButton(true); // Intro equivale a pulsar "Añadir"
    }

    public VistaProductos getVistaProductos() {
        return vistaProductos;
    }

    public VistaTotal getVistaTotal() {
        return vistaTotal;
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getPrecio() {
        return txtPrecio.getText();
    }

    public void limpiarFormulario() {
        txtNombre.clear();
        txtPrecio.clear();
        txtNombre.requestFocus();
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow()); // que salga centrada sobre esta ventana
        alerta.showAndWait();
    }

    public void alPulsarAgregar(Runnable accion) {
        btnAgregar.setOnAction(e -> accion.run());
    }

    public void alPulsarEliminar(Runnable accion) {
        btnEliminar.setOnAction(e -> accion.run());
    }
}
