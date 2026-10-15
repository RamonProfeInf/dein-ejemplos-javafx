package soluciones.agenda.vista;

import java.util.function.Function;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import soluciones.agenda.modelo.Contacto;

/**
 * VISTA de la agenda: buscador arriba, tabla en el centro y formulario
 * con los botones de alta, modificación y baja abajo.
 */
public class VistaAgenda extends BorderPane {

    private final TableView<Contacto> tabla = new TableView<>();

    private final TextField txtBuscar = new TextField();
    private final Button btnBuscar = new Button("Buscar");

    private final TextField txtNombre = new TextField();
    private final TextField txtTelefono = new TextField();
    private final TextField txtEmail = new TextField();

    private final Button btnAlta = new Button("Alta");
    private final Button btnModificar = new Button("Modificar");
    private final Button btnBaja = new Button("Baja");
    private final Button btnLimpiar = new Button("Limpiar");

    public VistaAgenda() {
        tabla.getColumns().add(columna("Nombre", 170, Contacto::nombre));
        tabla.getColumns().add(columna("Teléfono", 120, Contacto::telefono));
        tabla.getColumns().add(columna("Email", 220, Contacto::email));

        txtBuscar.setPrefColumnCount(15);
        HBox buscador = new HBox(5, new Label("Buscar:"), txtBuscar, btnBuscar);
        buscador.setAlignment(Pos.CENTER_LEFT);
        buscador.setPadding(new Insets(8));

        GridPane campos = new GridPane();
        campos.setHgap(5);
        campos.setVgap(5);
        ColumnConstraints etiquetas = new ColumnConstraints();
        ColumnConstraints cajas = new ColumnConstraints();
        cajas.setHgrow(Priority.ALWAYS); // las cajas de texto ocupan el ancho sobrante
        campos.getColumnConstraints().addAll(etiquetas, cajas);
        campos.addRow(0, new Label("Nombre:"), txtNombre);
        campos.addRow(1, new Label("Teléfono:"), txtTelefono);
        campos.addRow(2, new Label("Email:"), txtEmail);

        HBox botones = new HBox(5, btnAlta, btnModificar, btnBaja, btnLimpiar);
        botones.setAlignment(Pos.CENTER);

        TitledPane formulario = new TitledPane("Contacto", new VBox(8, campos, botones));
        formulario.setCollapsible(false);

        setTop(buscador);
        setCenter(tabla);
        setBottom(formulario);
        BorderPane.setMargin(tabla, new Insets(0, 8, 0, 8));
        BorderPane.setMargin(formulario, new Insets(8));
    }

    /** Crea una columna que muestra un campo de texto del contacto. */
    private static TableColumn<Contacto, String> columna(String titulo, double ancho,
            Function<Contacto, String> campo) {
        TableColumn<Contacto, String> columna = new TableColumn<>(titulo);
        columna.setCellValueFactory(fila -> new ReadOnlyStringWrapper(campo.apply(fila.getValue())));
        columna.setPrefWidth(ancho);
        return columna;
    }

    // ---------- Conexión con los datos ----------

    /**
     * Conecta la tabla con la lista observable que le pasen. El SortedList
     * permite ordenar pulsando en las cabeceras sin tocar el orden del modelo.
     */
    public void mostrar(ObservableList<Contacto> contactos) {
        SortedList<Contacto> ordenados = new SortedList<>(contactos);
        ordenados.comparatorProperty().bind(tabla.comparatorProperty());
        tabla.setItems(ordenados);
    }

    // ---------- Lectura de datos ----------

    public String getTextoBusqueda() {
        return txtBuscar.getText();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getTelefono() {
        return txtTelefono.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    /**
     * @return el contacto seleccionado o null si no hay ninguno. La TableView
     * devuelve directamente el objeto, no un número de fila.
     */
    public Contacto getSeleccionado() {
        return tabla.getSelectionModel().getSelectedItem();
    }

    // ---------- Pintar ----------

    public void rellenarFormulario(String nombre, String telefono, String email) {
        txtNombre.setText(nombre);
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
    }

    public void limpiarFormulario() {
        tabla.getSelectionModel().clearSelection();
        rellenarFormulario("", "", "");
        txtNombre.requestFocus();
    }

    public void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow());
        alerta.showAndWait();
    }

    /** Pregunta Sí/No. La vista solo pregunta; qué hacer con la respuesta lo decide el controlador. */
    public boolean confirmar(String pregunta) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, pregunta, ButtonType.YES, ButtonType.NO);
        alerta.setTitle("Confirmar");
        alerta.setHeaderText(null);
        alerta.initOwner(getScene().getWindow());
        return alerta.showAndWait().orElse(ButtonType.NO) == ButtonType.YES;
    }

    // ---------- Enganches para el controlador ----------

    public void alPulsarBuscar(Runnable accion) {
        btnBuscar.setOnAction(e -> accion.run());
        txtBuscar.setOnAction(e -> accion.run()); // Intro en el buscador
    }

    public void alPulsarAlta(Runnable accion) {
        btnAlta.setOnAction(e -> accion.run());
    }

    public void alPulsarModificar(Runnable accion) {
        btnModificar.setOnAction(e -> accion.run());
    }

    public void alPulsarBaja(Runnable accion) {
        btnBaja.setOnAction(e -> accion.run());
    }

    public void alPulsarLimpiar(Runnable accion) {
        btnLimpiar.setOnAction(e -> accion.run());
    }

    /** Avisa cada vez que cambia la fila seleccionada. */
    public void alSeleccionarFila(Runnable accion) {
        tabla.getSelectionModel().selectedItemProperty().addListener((propiedad, antes, ahora) -> accion.run());
    }
}
