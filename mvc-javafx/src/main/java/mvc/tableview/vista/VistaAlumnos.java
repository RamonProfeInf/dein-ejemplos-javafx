package mvc.tableview.vista;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import mvc.tableview.modelo.Alumno;

/**
 * VISTA: una TableView con los alumnos y un formulario para añadir otros nuevos.
 *
 * Cada columna dice qué dato del Alumno enseña, y la tabla trabaja directamente
 * con la lista de alumnos del modelo: cada fila es un objeto Alumno.
 */
public class VistaAlumnos extends BorderPane {

    private final TableView<Alumno> tabla = new TableView<>();
    private final TextField txtNombre = new TextField();
    private final TextField txtEdad = new TextField();
    private final TextField txtNota = new TextField();
    private final Button btnAgregar = new Button("Añadir alumno");
    private final Label lblMedia = new Label();

    public VistaAlumnos() {
        // Cada columna recibe una "fábrica de valores": dada una fila (un Alumno),
        // devuelve el dato que se pinta en esa celda.
        TableColumn<Alumno, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(fila -> new ReadOnlyObjectWrapper<>(fila.getValue().getNombre()));
        colNombre.setPrefWidth(260);

        TableColumn<Alumno, Integer> colEdad = new TableColumn<>("Edad");
        colEdad.setCellValueFactory(fila -> new ReadOnlyObjectWrapper<>(fila.getValue().getEdad()));
        colEdad.setPrefWidth(90);

        TableColumn<Alumno, Double> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(fila -> new ReadOnlyObjectWrapper<>(fila.getValue().getNota()));
        colNota.setPrefWidth(90);

        tabla.getColumns().add(colNombre);
        tabla.getColumns().add(colEdad);
        tabla.getColumns().add(colNota);

        txtNombre.setPrefColumnCount(12);
        txtEdad.setPrefColumnCount(3);
        txtNota.setPrefColumnCount(4);
        HBox formulario = new HBox(5,
                new Label("Nombre:"), txtNombre, new Label("Edad:"), txtEdad,
                new Label("Nota:"), txtNota, btnAgregar);
        formulario.setAlignment(Pos.CENTER_LEFT);
        formulario.setPadding(new Insets(8));

        setTop(formulario);
        setCenter(tabla);
        setBottom(lblMedia);
        BorderPane.setMargin(lblMedia, new Insets(6, 8, 6, 8));

        btnAgregar.setDefaultButton(true);
    }

    /**
     * Conecta la tabla con la lista observable del modelo. A partir de aquí,
     * si el modelo añade un alumno, aparece solo en la tabla: nadie tiene que
     * avisarla ni refrescarla.
     *
     * ¿Y el SortedList? Al pulsar en una cabecera, la TableView intenta ORDENAR
     * su lista. Como la del modelo es de solo lectura, le damos una "vista
     * ordenada" de ella: el usuario ordena lo que VE sin cambiar el modelo.
     */
    public void mostrar(ObservableList<Alumno> alumnos) {
        SortedList<Alumno> ordenados = new SortedList<>(alumnos);
        ordenados.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(ordenados);
    }

    /** Enlaza la etiqueta con la nota media: se actualiza sola. */
    public void mostrarMedia(ReadOnlyDoubleProperty media) {
        lblMedia.textProperty().bind(media.asString("Nota media: %.2f"));
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getEdad() {
        return txtEdad.getText();
    }

    public String getNota() {
        return txtNota.getText();
    }

    public void limpiarFormulario() {
        txtNombre.clear();
        txtEdad.clear();
        txtNota.clear();
        txtNombre.requestFocus();
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow());
        alerta.showAndWait();
    }

    public void alPulsarAgregar(Runnable accion) {
        btnAgregar.setOnAction(e -> accion.run());
    }
}
